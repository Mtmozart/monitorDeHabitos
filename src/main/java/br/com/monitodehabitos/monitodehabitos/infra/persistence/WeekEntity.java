package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "week")
public class WeekEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "total_percentage")
    private double totalPercentage;

    @Column(name = "percentage_per_day")
    private double percentagePerDay;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "week_id")
    private List<ProgressEntity> progresses = new ArrayList<>();


    public WeekEntity() {
    }

    public WeekEntity(Long id, LocalDate startDate, LocalDate endDate, double totalPercentage, double percentagePerDay, List<ProgressEntity> progresses) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPercentage = totalPercentage;
        this.percentagePerDay = percentagePerDay;
        this.progresses = progresses;
    }

    public Long getId() {
        return id;
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
