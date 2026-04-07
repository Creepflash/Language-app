package ee.MariEst.language_app.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginForm {

    @NotBlank(message = "E-post on kohustuslik")
    @Email(message = "Sisesta kehtiv e-posti aadress")
    private String email;

    @NotBlank(message = "Parool on kohustuslik")
    @Size(max = 128, message = "Parool on liiga pikk")
    private String password;
}
