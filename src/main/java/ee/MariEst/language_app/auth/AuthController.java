package ee.MariEst.language_app.auth;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ee.MariEst.language_app.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping
    public String authPage(
            @RequestParam(value = "tab", defaultValue = "login") String tab,
            @RequestParam(value = "registered", required = false) Boolean registered,
            @RequestParam(value = "error", required = false) String error,
            Model model,
            Authentication authentication) {
        if (authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }

        if (!model.containsAttribute("loginForm")) {
            model.addAttribute("loginForm", new LoginForm());
        }
        if (!model.containsAttribute("registerForm")) {
            model.addAttribute("registerForm", new RegisterForm());
        }
        model.addAttribute("activeTab", "register".equalsIgnoreCase(tab) ? "register" : "login");
        model.addAttribute("showRegisteredBanner", Boolean.TRUE.equals(registered));
        model.addAttribute("loginFailed", error != null);
        return "auth";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerForm") RegisterForm form, BindingResult bindingResult, Model model) {
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("activeTab", "register");
        model.addAttribute("showRegisteredBanner", false);
        model.addAttribute("loginFailed", false);

        if (form.getPassword() != null
                && form.getConfirmPassword() != null
                && !form.getPassword().equals(form.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "match", "Paroolid ei ühti");
        }

        if (bindingResult.hasErrors()) {
            return "auth";
        }

        try {
            userService.register(form);
        } catch (EmailAlreadyInUseException e) {
            bindingResult.rejectValue("email", "taken", "See e-post on juba kasutusel");
            return "auth";
        }

        return "redirect:/auth?registered=1&tab=login";
    }
}
