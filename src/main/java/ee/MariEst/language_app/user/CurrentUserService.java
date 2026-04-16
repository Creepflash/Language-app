package ee.MariEst.language_app.user;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import ee.MariEst.language_app.learning.LearningAccessException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private final UserRepository userRepository;

    public AppUser requireCurrentUser(Authentication authentication) {
        if (authentication == null || authentication.getName() == null) {
            throw new LearningAccessException("User is not authenticated");
        }
        return userRepository.findByEmailIgnoreCase(authentication.getName())
                .orElseThrow(() -> new LearningAccessException("Authenticated user not found"));
    }
}
