package ee.MariEst.language_app.learning;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAttemptRepository extends JpaRepository<UserAttempt, Long> {

    @Query("""
            select count(distinct ua.exerciseId)
            from UserAttempt ua
            where ua.userId = :userId and ua.lessonId = :lessonId
            """)
    long countDistinctExercisesAttempted(@Param("userId") Long userId, @Param("lessonId") Long lessonId);

    @Query("""
            select count(distinct ua.exerciseId)
            from UserAttempt ua
            where ua.userId = :userId and ua.lessonId = :lessonId and ua.correct = true
            """)
    long countDistinctCorrectExercises(@Param("userId") Long userId, @Param("lessonId") Long lessonId);
}
