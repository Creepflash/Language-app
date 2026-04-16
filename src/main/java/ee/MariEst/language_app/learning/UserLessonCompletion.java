package ee.MariEst.language_app.learning;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_lesson_completions")
@Getter
@Setter
@NoArgsConstructor
public class UserLessonCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "lesson_id", nullable = false)
    private Long lessonId;

    @Column(name = "completed_at", nullable = false)
    private Instant completedAt;

    @PrePersist
    void prePersist() {
        if (completedAt == null) {
            completedAt = Instant.now();
        }
    }
}
