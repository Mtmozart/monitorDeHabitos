package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import br.com.monitodehabitos.monitodehabitos.domain.entities.ProgressEnumStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "progress")
public class ProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "habit_day")
    private LocalDate currentDate;
    @Column(name = "completed")
    private ProgressEnumStatus completed;

    public ProgressEntity() {}

    public ProgressEntity(Long id, LocalDate currentDate, ProgressEnumStatus completed) {
        this.id = id;
        this.currentDate = currentDate;
        this.completed = completed;
    }

    public ProgressEntity(LocalDate currentDate, ProgressEnumStatus completed) {
        this.currentDate = currentDate;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }


    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public ProgressEnumStatus getCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return "ProgressEntity{" +
                "id=" + id +
                ", currentDate=" + currentDate +
                ", completed=" + completed +
                '}';
    }
}
