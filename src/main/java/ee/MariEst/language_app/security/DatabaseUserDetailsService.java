package ee.MariEst.language_app.security;

import java.util.Locale;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ee.MariEst.language_app.user.AppUser;
import ee.MariEst.language_app.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DatabaseUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String email = username.trim().toLowerCase(Locale.ROOT);
        AppUser appUser = userRepository
                .findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("Kasutajat ei leitud"));

        return User.withUsername(appUser.getEmail())
                .password(appUser.getPasswordHash())
                .disabled(!appUser.isEnabled())
                .authorities("ROLE_USER")
                .build();
    }
}
