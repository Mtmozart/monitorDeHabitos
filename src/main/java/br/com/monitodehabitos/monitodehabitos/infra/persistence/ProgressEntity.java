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
    @Column(name = "completed")
    private Boolean completed;

    public ProgressEntity() {}

    public ProgressEntity(Long id, HabitEntity habitEntity, LocalDate currentDate, Boolean completed) {
        this.id = id;
        this.habitEntity = habitEntity;
        this.currentDate = currentDate;
        this.completed = completed;
    }

    public ProgressEntity( LocalDate currentDate, Boolean completed) {
        this.currentDate = currentDate;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public HabitEntity getHabitEntity() {
        return habitEntity;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void addHabitEntity(HabitEntity habitEntity) {
        this.habitEntity = habitEntity;
    }

    @Override
    public String toString() {
        return "ProgressEntity{" +
                "currentDate=" + currentDate +
                ", completed=" + completed +
                '}';
    }
}
