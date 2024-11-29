package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import br.com.monitodehabitos.monitodehabitos.domain.entities.ProgressEnumStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProgressEntityRepository extends JpaRepository<ProgressEntity, String> {
    @Modifying
    @Query("UPDATE progress p SET p.completed = :status WHERE p.id = :progressId")
    void updateProgressStatus(String progressId, ProgressEnumStatus status);
}
