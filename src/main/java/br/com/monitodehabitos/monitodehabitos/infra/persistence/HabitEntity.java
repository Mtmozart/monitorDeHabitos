package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "habit")
@Table(name = "habit")
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

    @Column(name = "habit_day", nullable = false)
    private LocalDate currentDay;

    @OneToMany(mappedBy = "habitEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgressEntity> progressEntities = new ArrayList<>();

    public HabitEntity() {
    }

    public HabitEntity(Long id, String description, Boolean done, LocalDate start, LocalDate end, Double percentageForDay, ClientEntity clientEntity, LocalDate currentDay, List<ProgressEntity> progressEntities) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.percentageForDay = percentageForDay;
        this.clientEntity = clientEntity;
        this.currentDay = currentDay;
        this.progressEntities = progressEntities;
    }

    public HabitEntity(Long id, String description, Boolean done, LocalDate start, LocalDate end, Double percentageForDay, ClientEntity clientEntity, LocalDate currentDay) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.percentageForDay = percentageForDay;
        this.clientEntity = clientEntity;
        this.currentDay = currentDay;
        this.progressEntities = new ArrayList<>();
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

    public LocalDate getCurrentDay() {
        return currentDay;
    }

    public List<ProgressEntity> getProgressEntities() {
        return progressEntities;
    }

    public void addProgress(ProgressEntity progressEntity) {
        this.progressEntities.add(progressEntity);
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    @Override
    public String toString() {
        return "HabitEntity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", start=" + start +
                ", end=" + end +
                ", percentageForDay=" + percentageForDay +
                ", clientEntity=" + clientEntity +
                ", currentDay=" + currentDay +
                ", progressEntities=" + progressEntities.toString() +
                '}';
    }
}

