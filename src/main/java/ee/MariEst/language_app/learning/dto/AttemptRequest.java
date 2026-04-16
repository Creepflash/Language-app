package ee.MariEst.language_app.learning.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AttemptRequest(
        @NotNull Long lessonId,
        @NotNull Long exerciseId,
        @NotBlank String userAnswer) {
}
