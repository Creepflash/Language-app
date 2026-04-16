package ee.MariEst.language_app.learning;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByLessonIdOrderByOrderIndexAsc(Long lessonId);

    long countByLessonId(Long lessonId);
}
