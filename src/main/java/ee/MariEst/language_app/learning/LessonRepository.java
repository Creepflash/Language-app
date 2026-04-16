package ee.MariEst.language_app.learning;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAllByOrderByOrderIndexAsc();
}
