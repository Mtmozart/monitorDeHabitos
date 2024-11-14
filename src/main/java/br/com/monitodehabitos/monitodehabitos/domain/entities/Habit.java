package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.enums.HabitsErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Habit {
    private Long id;
    private String description;
    private Boolean done;
    private LocalDate start;
    private LocalDate end;
    private Double percentageForDay;
    private Client client;
    private LocalDate currentDay;
    private List<Progress> progress = new ArrayList<>();
    public Habit(Long id, String description, Boolean done, LocalDate start, LocalDate end, Double percentageForDay, Client client, LocalDate currentDay, List<Progress> progress) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.percentageForDay = percentageForDay;
        this.client = client;
        this.currentDay = currentDay;
        this.progress = progress;
    }
     //Contructor do update com data de ínicio
    public Habit(Long id, Client client, String description, LocalDate start) throws HabitExeption {
        this.id = id;
        this.description = description;
        this.done = false;
        this.start = start;
        this.end = calcEnd(start);
        this.percentageForDay = this.calcPercentageForDay();
        this.client = client;
        this.currentDay = this.start;
        this.progress = this.addProgress();
    }

    //Contructor do update sem data de ínicio
    public Habit(Long id, Client client, String description) throws HabitExeption {
        this.id = id;
        this.description = description;
        this.client = client;
    }

    public Habit(Habit habit) {
        this.id = habit.getId();
        this.description = habit.getDescription();
        this.done = habit.getDone();
        this.start = habit.getStart();
        this.end = habit.getEnd();
        this.percentageForDay = habit.getPercentageForDay();
        this.client = habit.getClient();
        this.currentDay = habit.currentDay;
    }


    public Long getId() {
        return id;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getDone() {
        return this.done;
    }

    public LocalDate getStart() {
        return this.start;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    public List<Progress> getProgress() {
        return progress;
    }

    public Double getPercentageForDay() {
        return this.percentageForDay;
    }

    public void changeDateStart(LocalDate newDate) throws HabitExeption {
        if (newDate != null) {
            this.start = newDate;
            this.end = this.calcEnd(newDate);
            this.percentageForDay = this.calcPercentageForDay();
        }
    }

    public void update(Habit updateHabit) throws HabitExeption {
        if (updateHabit == null) {
            throw new HabitExeption(HabitsErrorEnum.HBT0002.getMessage());
        }
        if (updateHabit.start != this.getStart() && updateHabit.start != null) {
            this.changeDateStart(updateHabit.getStart());
            this.getProgress().clear();
            updateHabit.getProgress().stream()
                    .map(p -> this.getProgress().add(p))
                    .collect(Collectors.toList());
        }
        if (!Objects.equals(updateHabit.description, this.description) && updateHabit.description != null) {
            this.description = updateHabit.getDescription();
        }
    }

    public boolean changeDo(LocalDate date) throws HabitExeption, WeekException {
        if (date == null) {
            throw new HabitExeption(HabitsErrorEnum.HBT0016.getMessage());
        }
        boolean isCompleted = this.progress.stream()
                .filter(n -> date.equals(n.getDate()))
                .findFirst()
                .map(Progress::changeStatusToCompleteOrNot)
                .orElse(false);

        this.done = this.progress.stream().allMatch(Progress::getCompleted);
        return isCompleted;
    }

    public LocalDate calcEnd(LocalDate start) throws HabitExeption {
        if (start != null) {
            return start.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        }
        this.end = this.start;
        return this.end;
    }

    public Double calcPercentageForDay() {
        if (this.start == null || this.end == null) {
            return 0.0;
        }
        long totalDays = this.start.until(this.end).getDays() + 1;

        if (totalDays > 0) {
            return Math.round((1.0 / totalDays) * 10000.0) / 100.0;
        } else {
            return 0.0;
        }
    }

    public LocalDate getCurrentDay() {
        return currentDay;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Progress> addProgress() {
        long qtdHabits = ChronoUnit.DAYS.between(this.getStart(), this.getEnd()) + 1;
        for (int i = 0; i < qtdHabits - 1; i++) {
            Progress progressObject = new Progress(currentDay.plusDays(i));
            this.progress.add(progressObject);
        }
        return this.progress;
    }

    @Override
    public String toString() {
        return "Habit{" + "id=" + id + ", description='" + description + '\'' + ", done=" + done + ", start=" + start + ", end=" + end + ", percentageForDay=" + percentageForDay + ", client=" + client + ", currentDay=" + currentDay + ", progress=" + progress.toString() + '}';
    }
}
