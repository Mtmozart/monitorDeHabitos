package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface WeekEntityRepository extends JpaRepository<WeekEntity, String> {
    boolean existsById(String id);

    void deleteById(String id);

    Optional<WeekEntity> findById(String id);
    //SELECT h FROM habit h WHERE h.clientEntity.id = :id


}
