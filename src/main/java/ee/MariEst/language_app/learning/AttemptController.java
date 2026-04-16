package ee.MariEst.language_app.learning;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ee.MariEst.language_app.learning.dto.AttemptRequest;
import ee.MariEst.language_app.learning.dto.AttemptResponse;
import ee.MariEst.language_app.user.AppUser;
import ee.MariEst.language_app.user.CurrentUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/attempts")
@RequiredArgsConstructor
public class AttemptController {

    private final AttemptService attemptService;
    private final CurrentUserService currentUserService;

    @PostMapping
    public AttemptResponse submitAttempt(@Valid @RequestBody AttemptRequest request, Authentication authentication) {
        AppUser currentUser = currentUserService.requireCurrentUser(authentication);
        return attemptService.submitAttempt(currentUser.getId(), request);
    }
}
