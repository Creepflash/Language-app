package ee.MariEst.language_app.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.MariEst.language_app.learning.dto.LessonDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final UserLessonCompletionRepository completionRepository;

    @Transactional(readOnly = true)
    public List<LessonDto> getLessonsForUser(Long userId) {
        List<Lesson> lessons = lessonRepository.findAllByOrderByOrderIndexAsc();
        Set<Long> completedIds = completionRepository.findCompletedLessonIds(userId);
        List<LessonDto> result = new ArrayList<>(lessons.size());

        boolean previousCompleted = true;
        boolean availablePicked = false;
        for (Lesson lesson : lessons) {
            boolean completed = completedIds.contains(lesson.getId());
            boolean locked = lesson.isLocked() || (!previousCompleted && !completed);
            boolean nextAvailable = false;
            if (!locked && !completed && !availablePicked) {
                nextAvailable = true;
                availablePicked = true;
            }

            result.add(new LessonDto(
                    lesson.getId(),
                    lesson.getTitle(),
                    lesson.getDescription(),
                    lesson.getOrderIndex(),
                    locked,
                    completed,
                    nextAvailable));

            previousCompleted = completed;
        }
        return result;
    }

    @Transactional(readOnly = true)
    public LessonDto getLessonForUser(Long lessonId, Long userId) {
        return getLessonsForUser(userId).stream()
                .filter(lesson -> lesson.id().equals(lessonId))
                .findFirst()
                .orElseThrow(() -> new LearningNotFoundException("Lesson not found"));
    }

    @Transactional(readOnly = true)
    public Long getNextLessonId(Long currentLessonId) {
        List<Lesson> lessons = lessonRepository.findAllByOrderByOrderIndexAsc();
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getId().equals(currentLessonId) && i + 1 < lessons.size()) {
                return lessons.get(i + 1).getId();
            }
        }
        return null;
    }

    @Transactional
    public void markLessonCompleted(Long userId, Long lessonId) {
        if (!completionRepository.existsByUserIdAndLessonId(userId, lessonId)) {
            UserLessonCompletion completion = new UserLessonCompletion();
            completion.setUserId(userId);
            completion.setLessonId(lessonId);
            completionRepository.save(completion);
        }
    }
}
