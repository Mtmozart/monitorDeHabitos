package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import br.com.monitodehabitos.monitodehabitos.domain.entities.ProgressEnumStatus;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "progress")
@Table(name = "progress")
public class ProgressEntity {

    @Id
    @Column(length = 36, nullable = false)
    private String id;
    @Column(name = "habit_day")
    private LocalDate currentDate;
    @Column(name = "completed")
    @Enumerated(EnumType.STRING)
    private ProgressEnumStatus completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_id")
    private WeekEntity week;

    public ProgressEntity() {
    }

    public ProgressEntity(String id, LocalDate currentDate, ProgressEnumStatus completed, WeekEntity week) {
        this.id = id;
        this.currentDate = currentDate;
        this.completed = completed;
        this.week = week;
    }

    public ProgressEntity(LocalDate currentDate, ProgressEnumStatus completed, WeekEntity week) {
        this.currentDate = currentDate;
        this.completed = completed;
        this.week = week;
    }

    public String getId() {
        return id;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public ProgressEnumStatus getCompleted() {
        return completed;
    }

    public WeekEntity getWeek() {
        return week;
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
