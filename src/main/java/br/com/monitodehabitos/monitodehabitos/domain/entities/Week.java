package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.enums.WeekErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.domain.observer.Observer;
import br.com.monitodehabitos.monitodehabitos.domain.observer.Subject;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.util.List;

public class Week implements Observer {
    private Long id;

    @OneToOne
    @JoinColumn(name = "perfil_id", referencedColumnName = "id")
    private Habit habit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
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
    public void update(Habit habit, boolean change) throws WeekException {
        if (!change) {
            this.subtractPercentage(habit.getPercentageForDay());
        } else {
            this.addPercentage(habit.getPercentageForDay());
        }
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
