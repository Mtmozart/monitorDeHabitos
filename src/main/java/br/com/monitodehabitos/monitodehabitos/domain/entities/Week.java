package br.com.monitodehabitos.monitodehabitos.domain.entities;

public class Week {
    private Long id;
    private Habit habit;
    private Client client;
    private final int weekNumber = 1;
    private double totalePercentage = 100.00;
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

    public int getWeekNumber() {
        return weekNumber;
    }

    public double getTotalePercentage() {
        return totalePercentage;
    }

    public void addPercentage(double add) {
        this.totalePercentage = this.totalePercentage + add;
    }

    public void subtractPercentage(double subtract) {
        this.totalePercentage = this.totalePercentage - subtract;
    }

    @Override
    public String toString() {
        return "Week{" +
                "id=" + id +
                ", habit=" + habit +
                ", client=" + client +
                ", weekNumber=" + weekNumber +
                ", totalePercentage=" + totalePercentage +
                '}';
    }
}
