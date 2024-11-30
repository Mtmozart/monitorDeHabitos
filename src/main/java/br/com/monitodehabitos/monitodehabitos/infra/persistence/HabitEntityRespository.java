package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import br.com.monitodehabitos.monitodehabitos.domain.entities.HabitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HabitEntityRespository extends JpaRepository<HabitEntity, String>  {
    @Query("SELECT h FROM habit h WHERE h.clientEntity.id = :id")
    List<HabitEntity> findAllByClientId(String id);

    @Query("SELECT h FROM habit h WHERE h.id = :habitId ")
    Optional<HabitEntity> findHabit(String habitId);


    @Modifying
    @Query("UPDATE habit h SET h.done = :done WHERE h.id = :habitId")
    void updateHabitStatus(String habitId, HabitStatus done);

    @Modifying
    @Query("UPDATE habit h SET h.description = :description, h.done = :done WHERE h.id = :habitId")
    void updateHabitStatusAndDone(String habitId, String description, HabitStatus done);

    boolean existsById(String id);

    void deleteById(String id);
}
