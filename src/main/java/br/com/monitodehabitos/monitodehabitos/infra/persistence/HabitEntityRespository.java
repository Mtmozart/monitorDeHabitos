package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HabitEntityRespository extends JpaRepository<HabitEntity, String>  {
    @Query("SELECT h FROM habit h WHERE h.clientEntity.id = :id")
    List<HabitEntity> findAllByClientId(String id);

    @Query("SELECT h FROM habit h WHERE h.id = :habitId ")
    Optional<HabitEntity> findHabit(String habitId);

    boolean existsById(String id);

    void deleteById(String id);
}
