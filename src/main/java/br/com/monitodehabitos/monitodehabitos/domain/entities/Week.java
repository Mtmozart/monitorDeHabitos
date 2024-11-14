package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.enums.WeekErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

public class Week {
    private Long id;
    private Habit habit;
    private Client client;
    private double totalePercentage = 00.00;

    public Week(Long id, Habit habit, Client client) {
        this.id = id;
        this.habit = habit;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public Habit getHabit() {
        return habit;
    }

    public Client getClient() {
        return client;
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
                ", habit=" + habit +
                ", client=" + client +
                ", totalePercentage=" + totalePercentage +
                '}';
    }
}
