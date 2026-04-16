package ee.MariEst.language_app.learning;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.MariEst.language_app.learning.dto.ExerciseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Transactional(readOnly = true)
    public List<ExerciseDto> getExercisesByLesson(Long lessonId) {
        return exerciseRepository.findByLessonIdOrderByOrderIndexAsc(lessonId).stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Exercise requireExercise(Long exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new LearningNotFoundException("Exercise not found"));
    }

    @Transactional(readOnly = true)
    public long countLessonExercises(Long lessonId) {
        return exerciseRepository.countByLessonId(lessonId);
    }

    private ExerciseDto toDto(Exercise exercise) {
        return new ExerciseDto(
                exercise.getId(),
                exercise.getLesson().getId(),
                exercise.getType(),
                exercise.getQuestion(),
                parseOptions(exercise.getOptionsJson()),
                exercise.getOrderIndex());
    }

    private List<String> parseOptions(String optionsJson) {
        if (optionsJson == null || optionsJson.isBlank()) {
            return List.of();
        }
        try {
            String normalized = optionsJson.trim();
            if (normalized.startsWith("[") && normalized.endsWith("]")) {
                normalized = normalized.substring(1, normalized.length() - 1);
            }
            if (normalized.isBlank()) {
                return List.of();
            }
            return List.of(normalized.split(",")).stream()
                    .map(String::trim)
                    .map(option -> option.replaceAll("^\"|\"$", ""))
                    .filter(option -> !option.isBlank())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
