package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "habit")
public class HabitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Boolean done;
    private LocalDate start;
    private LocalDate end;
    private int day;
    @Column(name = "percentage_for_day")
    private Double percentageForDay;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    private LocalDate currentDay;

    public HabitEntity() {
    }

    public HabitEntity(Long id, String description, Boolean done, LocalDate start, LocalDate end, int day, Double percentageForDay, ClientEntity clientEntity, LocalDate currentDay) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.day = day;
        this.percentageForDay = percentageForDay;
        this.clientEntity = clientEntity;
        this.currentDay = currentDay;
    }


    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getDone() {
        return done;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public Double getPercentageForDay() {
        return percentageForDay;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public int getDay() {
        return day;
    }

    public LocalDate getCurrentDay() {
        return currentDay;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }
}

