package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.enums.WeekErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Week {
    private Long id;
    private List<Habit> habits;
    private Client client;
    private final int weekNumber = 1;
    private double totalePercentage = 00.00;


    public Week(Long id, List<Habit> habits, Client client) {
        this.id = id;
        this.habits = habits;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public List<Habit> getHabit() {
        return habits;
    }

    public Client getClient() {
        return client;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void addHabit(Habit habit) {

        long qtdHabits = ChronoUnit.DAYS.between(habit.getStart(), habit.getEnd()) + 1;

        for (int i = 1; i < qtdHabits; i++) {
            Habit newHabit = new Habit(habit);
            newHabit.setDay(habit.getDay() + i);
            newHabit.setCurrentDay(habit.getStart().plusDays(i));
            habits.add(newHabit);
        }
    }

    public double getTotalePercentage() {
        return totalePercentage;
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

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Week{" +
                "id=" + id +
                ", habit=" + habits +
                ", client=" + client +
                ", weekNumber=" + weekNumber +
                ", totalePercentage=" + totalePercentage +
                '}';
    }
}
