package ee.MariEst.language_app.learning;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LearningExceptionHandler {

    @ExceptionHandler(LearningNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(LearningNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(LearningAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAccess(LearningAccessException ex) {
        return ex.getMessage();
    }
}
