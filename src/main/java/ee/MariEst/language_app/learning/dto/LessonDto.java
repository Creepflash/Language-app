package ee.MariEst.language_app.learning.dto;

public record LessonDto(
        Long id,
        String title,
        String description,
        Integer orderIndex,
        boolean locked,
        boolean completed,
        boolean nextAvailable) {
}
