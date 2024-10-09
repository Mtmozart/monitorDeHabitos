package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.enums.HabitsErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class Habit {
    private Long id;
    private String description;
    private Boolean done;
    private LocalDate start;
    private LocalDate end;
    private int day;
    private Double percentageForDay;
    private Client client;
    private LocalDate currentDay;
    private Progress progress;


    public Habit(Long id, String description, Boolean done, LocalDate start, LocalDate end, int day, Double percentageForDay, Client client, LocalDate currentDay, Progress progress) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.day = day;
        this.percentageForDay = percentageForDay;
        this.client = client;
        this.currentDay = currentDay;
        this.progress = progress;
    }

    public Habit(Long id, Client client, String description, LocalDate start) throws HabitExeption {
        this.id = id;
        this.description = description;
        this.done = false;
        this.start = start;
        this.end = calcEnd(start);
        this.percentageForDay = this.calcPercentageForDay();
        this.client = client;
        this.day = 1;
        this.currentDay = this.start;
    }

    public Habit(Habit habit) {
        this.id = habit.getId();
        this.description = habit.getDescription();
        this.done = habit.getDone();
        this.start = habit.getStart();
        this.end = habit.getEnd();
        this.percentageForDay = habit.getPercentageForDay();
        this.client = habit.getClient();
        this.day = habit.getDay();
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
        }
        if (updateHabit.description != this.description && updateHabit.description != null) {
            this.description = updateHabit.getDescription();
        }
    }

    public void changeDo() {
        this.done = !this.done;
    }

    public LocalDate calcEnd(LocalDate start) throws HabitExeption {
        if (start != null) {
            return start.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public LocalDate getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(LocalDate currentDay) {
        this.currentDay = currentDay;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addProgress(){
        long qtdHabits = ChronoUnit.DAYS.between(this.getStart(), this.getEnd()) + 1;
        for (int i = 1; i < qtdHabits; i++) {
            this.progress.addPercentage(currentDay.plusDays(i));
        }
    }

    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", start=" + start +
                ", end=" + end +
                ", currentDay " + currentDay +
                ", percentageForDay=" + percentageForDay +
                ", client=" + client.toString() +
                '}';
    }
}
