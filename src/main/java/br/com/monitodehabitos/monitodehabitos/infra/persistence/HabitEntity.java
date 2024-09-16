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
    @Column(name = "percentage_for_day")
    private Double percentageForDay;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    public HabitEntity() {
    }

    public HabitEntity(Long id, String description, Boolean done, LocalDate start, LocalDate end, Double percentageForDay, ClientEntity clientEntity) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.percentageForDay = percentageForDay;
        this.clientEntity = clientEntity;
    }

    public HabitEntity(ClientEntity clientEntity, String description, Boolean done, LocalDate start, LocalDate end, Double percentageForDay) {
        this.clientEntity = clientEntity;
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.percentageForDay = percentageForDay;
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

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }
}

