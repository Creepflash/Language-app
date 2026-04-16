package ee.MariEst.language_app.learning;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ee.MariEst.language_app.learning.dto.ExerciseDto;
import ee.MariEst.language_app.learning.dto.LessonDto;
import ee.MariEst.language_app.user.AppUser;
import ee.MariEst.language_app.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final LessonService lessonService;
    private final CurrentUserService currentUserService;

    @GetMapping
    public List<ExerciseDto> getExercises(@RequestParam Long lessonId, Authentication authentication) {
        AppUser currentUser = currentUserService.requireCurrentUser(authentication);
        LessonDto lesson = lessonService.getLessonForUser(lessonId, currentUser.getId());
        if (lesson.locked()) {
            throw new LearningAccessException("Lesson is locked");
        }
        return exerciseService.getExercisesByLesson(lessonId);
    }
}
