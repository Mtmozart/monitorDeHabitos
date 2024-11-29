package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface WeekEntityRepository extends JpaRepository<WeekEntity, String> {
    boolean existsById(String id);

    void deleteById(String id);

    Optional<WeekEntity> findById(String id);
    @Modifying
    @Query("UPDATE week w SET w.totalPercentage = :percentage WHERE w.id = :id")
    void updateProgressWeek(String id, double percentage);

}
