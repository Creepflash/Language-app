package ee.MariEst.language_app.auth;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterForm {

    @NotBlank(message = "E-post on kohustuslik")
    @Email(message = "Sisesta kehtiv e-posti aadress")
    private String email;

    @NotBlank(message = "Parool on kohustuslik")
    @Size(min = 8, max = 128, message = "Parool: 8–128 tähemärki")
    private String password;

    @NotBlank(message = "Korda parooli")
    private String confirmPassword;

    @AssertTrue(message = "Pead nõustuma kasutustingimustega")
    private boolean acceptTerms;
}
