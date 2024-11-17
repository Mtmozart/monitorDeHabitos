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
    @JoinColumn(name = "week_id", nullable = false)
    private WeekEntity week;
    @Column(name = "habit_day")
    private LocalDate currentDate;
    @Column(name = "completed")
    private Boolean completed;

    public ProgressEntity() {}

    public ProgressEntity(Long id, WeekEntity week, LocalDate currentDate, Boolean completed) {
        this.id = id;
        this.week = week;
        this.currentDate = currentDate;
        this.completed = completed;
    }

    public ProgressEntity(LocalDate currentDate, Boolean completed) {
        this.currentDate = currentDate;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public WeekEntity getWeek() {
        return week;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void addWeekEntity(WeekEntity weekEntity) {
        this.week = weekEntity;
    }

    @Override
    public String toString() {
        return "ProgressEntity{" +
                "id=" + id +
                ", week=" + week +
                ", currentDate=" + currentDate +
                ", completed=" + completed +
                '}';
    }
}
