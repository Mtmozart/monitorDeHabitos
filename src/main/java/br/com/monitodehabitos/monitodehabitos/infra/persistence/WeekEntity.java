package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "week")
public class WeekEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne()
    private HabitEntity habitEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;
    @Column(name = "number_week")
    private int number;
    @Column(name = "totale_percentage")
    private double totalePercentage;

    public WeekEntity() {
    }

    public WeekEntity(Long id, HabitEntity habitEntity, ClientEntity clientEntity, int number, double totalePercentage) {
        this.id = id;
        this.habitEntity = habitEntity;
        this.clientEntity = clientEntity;
        this.number = number;
        this.totalePercentage = totalePercentage;
    }

    public Long getId() {
        return id;
    }

    public HabitEntity getHabitEntity() {
        return habitEntity;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public int getNumber() {
        return number;
    }

    public double getTotalePercentage() {
        return totalePercentage;
    }
}
