package ee.MariEst.language_app.learning;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.MariEst.language_app.learning.dto.AttemptRequest;
import ee.MariEst.language_app.learning.dto.AttemptResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttemptService {

    private final ExerciseService exerciseService;
    private final UserAttemptRepository attemptRepository;
    private final LessonService lessonService;

    @Transactional
    public AttemptResponse submitAttempt(Long userId, AttemptRequest request) {
        Exercise exercise = exerciseService.requireExercise(request.exerciseId());
        if (!exercise.getLesson().getId().equals(request.lessonId())) {
            throw new LearningAccessException("Exercise does not belong to this lesson");
        }

        String normalizedUserAnswer = request.userAnswer().trim();
        String normalizedCorrect = exercise.getCorrectAnswer().trim();
        boolean isCorrect = normalizedCorrect.equalsIgnoreCase(normalizedUserAnswer);

        UserAttempt attempt = new UserAttempt();
        attempt.setUserId(userId);
        attempt.setLessonId(request.lessonId());
        attempt.setExerciseId(request.exerciseId());
        attempt.setUserAnswer(request.userAnswer());
        attempt.setCorrect(isCorrect);
        attemptRepository.save(attempt);

        long totalExercises = exerciseService.countLessonExercises(request.lessonId());
        long attemptedExercises = attemptRepository.countDistinctExercisesAttempted(userId, request.lessonId());
        long score = attemptRepository.countDistinctCorrectExercises(userId, request.lessonId());
        boolean lessonCompleted = attemptedExercises >= totalExercises && totalExercises > 0;
        if (lessonCompleted) {
            lessonService.markLessonCompleted(userId, request.lessonId());
        }

        return new AttemptResponse(
                isCorrect,
                exercise.getCorrectAnswer(),
                lessonCompleted,
                score,
                totalExercises,
                lessonCompleted ? lessonService.getNextLessonId(request.lessonId()) : null);
    }
}
