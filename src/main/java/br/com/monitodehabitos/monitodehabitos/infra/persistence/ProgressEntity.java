package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "progress")
public class ProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "habit_id", nullable = false)
    private HabitEntity habitEntity;
    @Column(name = "habit_day")
    private LocalDate currentDate;
    private Boolean completed;

    public ProgressEntity(LocalDate currentDate, Boolean completed) {
        this.currentDate = currentDate;
        this.completed = completed;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public Boolean getCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return "ProgressEntity{" +
                "currentDate=" + currentDate +
                ", completed=" + completed +
                '}';
    }
}
