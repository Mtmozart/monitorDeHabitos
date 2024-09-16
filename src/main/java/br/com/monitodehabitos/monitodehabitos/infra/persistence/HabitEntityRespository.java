package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitEntityRespository extends JpaRepository<HabitEntity, Long> {
}
