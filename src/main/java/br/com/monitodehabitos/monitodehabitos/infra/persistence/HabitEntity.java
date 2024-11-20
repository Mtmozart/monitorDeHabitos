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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @Enumerated(EnumType.STRING)
    private HabitStatus done;

    private LocalDate start;
    private LocalDate end;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "habit_id")
    private List<WeekEntity> weeks = new ArrayList<>();

    public HabitEntity() {
    }

    public HabitEntity(Long id, String description, HabitStatus done, LocalDate start, LocalDate end, ClientEntity clientEntity, List<WeekEntity> weeks) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.clientEntity = clientEntity;
        this.weeks = weeks;
    }

    public HabitEntity(Long id, String description, HabitStatus done, LocalDate start, LocalDate end, ClientEntity clientEntity) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.clientEntity = clientEntity;
    }

    public Long getId() {
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

