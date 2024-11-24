package br.com.monitodehabitos.monitodehabitos.infra.persistence;


import br.com.monitodehabitos.monitodehabitos.domain.entities.HabitStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "habit")
@Table(name = "habit")
public class HabitEntity {
    @Id
    @Column(length = 36, nullable = false)
    private String id;
    private String description;

    @Enumerated(EnumType.STRING)
    private HabitStatus done;

    private LocalDate start;
    private LocalDate end;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;
    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WeekEntity> weeks = new ArrayList<>();

    public HabitEntity() {
    }

    public HabitEntity(String id, String description, HabitStatus done, LocalDate start, LocalDate end, ClientEntity clientEntity, List<WeekEntity> weeks) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.clientEntity = clientEntity;
        this.weeks = weeks;
    }

    public HabitEntity(String id, String description, HabitStatus done, LocalDate start, LocalDate end, ClientEntity clientEntity) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.clientEntity = clientEntity;
    }


    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


    public LocalDate getStart() {
        return start;
    }

    public HabitStatus getDone() {
        return done;
    }

    public LocalDate getEnd() {
        return end;
    }

    public List<WeekEntity> getWeeks() {
        return weeks;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
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
                ", clientEntity=" + clientEntity +
                ", weeks=" + weeks +
                '}';
    }
}

