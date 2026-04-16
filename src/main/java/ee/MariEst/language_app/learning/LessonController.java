package ee.MariEst.language_app.learning;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ee.MariEst.language_app.learning.dto.LessonDto;
import ee.MariEst.language_app.user.AppUser;
import ee.MariEst.language_app.user.CurrentUserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;
    private final CurrentUserService currentUserService;

    @GetMapping
    public List<LessonDto> getLessons(Authentication authentication) {
        AppUser currentUser = currentUserService.requireCurrentUser(authentication);
        return lessonService.getLessonsForUser(currentUser.getId());
    }

    @GetMapping("/{lessonId}")
    public LessonDto getLesson(@PathVariable Long lessonId, Authentication authentication) {
        AppUser currentUser = currentUserService.requireCurrentUser(authentication);
        return lessonService.getLessonForUser(lessonId, currentUser.getId());
    }
}
