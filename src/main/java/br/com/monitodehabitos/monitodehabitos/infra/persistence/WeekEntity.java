package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "week")
@Table(name = "week")
public class WeekEntity {
    @Id
    @Column(length = 36, nullable = false)
    private String id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "total_percentage")
    private double totalPercentage;

    @Column(name = "percentage_per_day")
    private double percentagePerDay;
    @ManyToOne
    @JoinColumn(name = "habit_id", nullable = false)
    private HabitEntity habit;
    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgressEntity> progresses = new ArrayList<>();


    public WeekEntity() {
    }

    public WeekEntity(String id, LocalDate startDate, LocalDate endDate, double totalPercentage, HabitEntity habitEntity, double percentagePerDay, List<ProgressEntity> progresses) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPercentage = totalPercentage;
        this.habit = habitEntity;
        this.percentagePerDay = percentagePerDay;
        this.progresses = progresses;
    }
    public String getId() {
        return id;
    }

    public HabitEntity getHabit() {
        return habit;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalPercentage() {
        return totalPercentage;
    }

    public double getPercentagePerDay() {
        return percentagePerDay;
    }

    public List<ProgressEntity> getProgresses() {
        return progresses;
    }

    public void setHabit(HabitEntity habit) {
        this.habit = habit;
    }

    @Override
    public String toString() {
        return "WeekEntity{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalPercentage=" + totalPercentage +
                ", percentagePerDay=" + percentagePerDay +
                ", progresses=" + progresses +
                '}';
    }
}
