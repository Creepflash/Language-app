package ee.MariEst.language_app.learning.dto;

import java.util.List;

import ee.MariEst.language_app.learning.ExerciseType;

public record ExerciseDto(
        Long id,
        Long lessonId,
        ExerciseType type,
        String question,
        List<String> options,
        Integer orderIndex) {
}
