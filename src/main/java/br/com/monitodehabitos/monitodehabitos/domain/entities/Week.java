package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.enums.WeekErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Week {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double percentagePerDay;
    private List<Progress> progresses;
    private double totalePercentage = 00.00;

    public Week(Long id, LocalDate startDate, LocalDate endDate, double percentagePerDay, List<Progress> progresses, double totalePercentage) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentagePerDay = percentagePerDay;
        this.progresses = progresses;
        this.totalePercentage = totalePercentage;
    }

    public Week(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentagePerDay = calcPercentageForDay();
        this.progresses = addProgress(startDate, endDate);
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

    public List<Progress> getProgresses() {
        return progresses;
    }

    public double getTotalePercentage() {
        return totalePercentage;
    }



    public List<Progress> addProgress(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("A data inicial deve ser anterior ou igual à data final.");
        }
        long qtdHabits = ChronoUnit.DAYS.between(start, end) + 1;
        for (int i = 0; i < qtdHabits; i++) {
            Progress progress = new Progress(null, start.plusDays(i), ProgressEnumStatus.NOT_STARTED);
            this.progresses.add(progress);
        }
        return this.progresses;
    }


    public void addPercentage(double add) throws WeekException {
        if (add < 0) {
            throw new WeekException(WeekErrorEnum.HBT0002.getMessage());
        }
        if (this.totalePercentage < 100.00) {
            this.totalePercentage += add;
        }
        var surplus = this.totalePercentage + add;
        if (surplus > 100) {
            this.totalePercentage = 100.00;
        }
    }

    public void subtractPercentage(double subtract) throws WeekException {
        if (subtract < 0) {
            throw new WeekException(WeekErrorEnum.HBT0002.getMessage());
        }
        if (this.totalePercentage > 0) {
            this.totalePercentage = this.totalePercentage - subtract;
        }
        var minor = this.totalePercentage - subtract;
        if (minor < 0) {
            this.totalePercentage = 0.00;
        }
    }

    public double calcPercentageForDay() {
        if (this.startDate == null || this.endDate == null) {
            return 0.0;
        }
        long totalDays = this.startDate.until(this.endDate).getDays() + 1;
        if (totalDays > 0) {
            return Math.round((1.0 / totalDays) * 10000.0) / 100.0;
        } else {
            return 0.0;
        }
    }

    @Override
    public String toString() {
        return "Week{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", progresses=" + progresses +
                ", totalePercentage=" + totalePercentage +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Week week)) return false;

        if (Double.compare(percentagePerDay, week.percentagePerDay) != 0) return false;
        if (Double.compare(getTotalePercentage(), week.getTotalePercentage()) != 0) return false;
        if (!getId().equals(week.getId())) return false;
        if (!getStartDate().equals(week.getStartDate())) return false;
        if (!getEndDate().equals(week.getEndDate())) return false;
        return getProgresses().equals(week.getProgresses());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId().hashCode();
        result = 31 * result + getStartDate().hashCode();
        result = 31 * result + getEndDate().hashCode();
        temp = Double.doubleToLongBits(percentagePerDay);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getProgresses().hashCode();
        temp = Double.doubleToLongBits(getTotalePercentage());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
