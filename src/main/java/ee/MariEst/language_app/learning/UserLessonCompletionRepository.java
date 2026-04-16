package ee.MariEst.language_app.learning;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserLessonCompletionRepository extends JpaRepository<UserLessonCompletion, Long> {

    boolean existsByUserIdAndLessonId(Long userId, Long lessonId);

    @Query("""
            select ulc.lessonId
            from UserLessonCompletion ulc
            where ulc.userId = :userId
            """)
    Set<Long> findCompletedLessonIds(@Param("userId") Long userId);
}
