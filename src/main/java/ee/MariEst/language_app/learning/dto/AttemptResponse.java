package ee.MariEst.language_app.learning.dto;

public record AttemptResponse(
        boolean correct,
        String correctAnswer,
        boolean lessonCompleted,
        long score,
        long totalExercises,
        Long nextLessonId) {
}
