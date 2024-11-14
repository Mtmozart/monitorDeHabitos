package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HabitEntityRespository extends JpaRepository<HabitEntity, Long>  {
    @Query("SELECT h FROM habit h WHERE h.clientEntity.id = :id")
    List<HabitEntity> findAllByClientId(Long id);

    @Query("SELECT h FROM habit h WHERE h.id = :habitId ")
    Optional<HabitEntity> findHabit(Long habitId);

}
