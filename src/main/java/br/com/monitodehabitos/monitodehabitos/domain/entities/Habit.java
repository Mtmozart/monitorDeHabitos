package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.enums.HabitsErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Habit {
    private Long id;
    private String description;
    private Boolean done;
    private LocalDate start;
    private LocalDate end;
    private Double percentageForDay;
    private Client client;


    public Habit(Long id, String description, Boolean done, LocalDate start, LocalDate end, Double percentageForDay, Client client) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.percentageForDay = percentageForDay;
        this.client = client;
    }

    public Habit(Long id, Client client, String description, LocalDate start) throws HabitExeption {
        this.id = id;
        this.description = description;
        this.done = false;
        this.start = start;
        this.end = calcEnd(start);
        this.percentageForDay = this.calcPercentageForDay();
        this.client = client;
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
        if (newDate == null) {
            throw new HabitExeption(HabitsErrorEnum.HBT0007.getMessage());
        }
        this.start = newDate;
        this.end = this.calcEnd(newDate);
        this.percentageForDay = this.calcPercentageForDay();
    }

    public void changeDo() {
        this.done = !this.done;
    }

    public LocalDate calcEnd(LocalDate start) throws HabitExeption {
        if (start == null) {
            throw new HabitExeption(HabitsErrorEnum.HBT0009.getMessage());
        }
        return start.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    }

    public Double calcPercentageForDay() {
        long totalDays = this.start.until(this.end).getDays() + 1;
        if (totalDays > 0) {
            return Math.round((1.0 / totalDays) * 10000.0) / 100.0;
        } else {
            return 0.0;
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", start=" + start +
                ", end=" + end +
                ", percentageForDay=" + percentageForDay +
                ", client=" + client.toString() +
                '}';
    }
}
