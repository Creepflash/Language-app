package ee.MariEst.language_app.auth;

public class EmailAlreadyInUseException extends RuntimeException {

    public EmailAlreadyInUseException() {
        super("Email already registered");
    }
}
