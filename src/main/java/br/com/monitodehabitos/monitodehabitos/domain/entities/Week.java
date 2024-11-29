package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.enums.WeekErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.ProgressException;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Week {
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double percentagePerDay;
    private List<Progress> progresses = new ArrayList<>();
    private Habit habit;
    private double totalPercentage;

    public Week(String id, LocalDate startDate, LocalDate endDate, double percentagePerDay, List<Progress> progresses, double totalPercentage) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentagePerDay = percentagePerDay;
        this.progresses = progresses;
        this.totalPercentage = totalPercentage;
    }

    public Week(String id, LocalDate startDate, LocalDate endDate, double percentagePerDay, List<Progress> progresses, Habit habit, double totalPercentage) {
        this.id = id;
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("A data inicial deve ser anterior ou igual à data final.");
        }
        this.endDate = endDate;
        this.percentagePerDay = percentagePerDay;
        this.progresses = progresses;
        this.habit = habit;
        this.totalPercentage = totalPercentage;
    }

    public Week(LocalDate startDate, LocalDate endDate, Habit habit) {
        this.id = UUID.randomUUID().toString();
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("A data inicial deve ser anterior ou igual à data final.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentagePerDay = calcPercentageForDay();
        this.habit = habit;
        this.progresses = addProgress(startDate, endDate);
    }
    public String getId() {
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

    public double getTotalPercentage() {
        return totalPercentage;
    }

    public double getPercentagePerDay() {
        return percentagePerDay;
    }

    public Habit getHabit() {
        return habit;
    }

    public List<Progress> addProgress(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("A data inicial deve ser anterior ou igual à data final.");
        }
        long qtdHabits = ChronoUnit.DAYS.between(start, end) + 1;
        for (int i = 0; i < qtdHabits; i++) {
            Progress progress = new Progress(start.plusDays(i), ProgressEnumStatus.NOT_STARTED, this);
            this.progresses.add(progress);
        }
        return this.progresses;
    }

    private void validatePercentage(double newPercentage) throws WeekException {
        if (newPercentage < 0 || newPercentage > 100) {
            throw new WeekException(WeekErrorEnum.WK0002.getMessage());
        }
        this.totalPercentage = newPercentage;
    }

    public void addPercentage(double add) throws WeekException {
        validatePercentage(Math.min(this.totalPercentage + add, 100.0));
    }

    public void subtractPercentage(double subtract) throws WeekException {
        validatePercentage(Math.max(this.totalPercentage - subtract, 0.0));
    }

    public double calcPercentageForDay() {
        if (this.startDate == null || this.endDate == null) {
            return 0.0;
        }
        long totalDays = ChronoUnit.DAYS.between(this.startDate, this.endDate) + 1;
        return totalDays > 0 ? Math.round((1.0 / totalDays) * 10000.0) / 100.0 : 0.0;
    }

    public Progress changeProgressStatus(LocalDate date) throws ProgressException {
        Optional<Progress> progress = progresses.stream()
                .filter(p -> p.getDate().equals(date))
                .findFirst();
        if(progress.isEmpty()){
            throw new ProgressException("Progresso não encontrado");
        }
        progress.get().changeStatusToCompleteOrNot();
        return progress.get();
    }

    @Override
    public String toString() {
        return "Week{" +
                "id='" + id + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", percentagePerDay=" + percentagePerDay +
                ", progresses=" + progresses +
                ", habit=" + habit +
                ", totalPercentage=" + totalPercentage +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Week week)) return false;

        if (Double.compare(percentagePerDay, week.percentagePerDay) != 0) return false;
        if (Double.compare(getTotalPercentage(), week.getTotalPercentage()) != 0) return false;
        if (getId() != null ? !getId().equals(week.getId()) : week.getId() != null) return false;
        if (getStartDate() != null ? !getStartDate().equals(week.getStartDate()) : week.getStartDate() != null)
            return false;
        if (getEndDate() != null ? !getEndDate().equals(week.getEndDate()) : week.getEndDate() != null) return false;
        return getProgresses() != null ? getProgresses().equals(week.getProgresses()) : week.getProgresses() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getStartDate() != null ? getStartDate().hashCode() : 0);
        result = 31 * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
        temp = Double.doubleToLongBits(percentagePerDay);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getProgresses() != null ? getProgresses().hashCode() : 0);
        temp = Double.doubleToLongBits(getTotalPercentage());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
