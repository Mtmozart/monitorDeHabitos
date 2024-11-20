package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WeekEntityRepository extends JpaRepository<WeekEntity,Long> {
    //SELECT h FROM habit h WHERE h.clientEntity.id = :id


}
