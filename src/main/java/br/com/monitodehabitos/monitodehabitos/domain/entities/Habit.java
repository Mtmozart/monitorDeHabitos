package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.enums.HabitsErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Habit {
    private String id;
    private String description;
    private HabitStatus done;
    private LocalDate start;
    private LocalDate end;
    private Client client;
    private List<Week> weeks = new ArrayList<>();

    public Habit(String id, String description, HabitStatus done, LocalDate start, LocalDate end, Client client, List<Week> weeks) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.client = client;
        this.weeks = weeks;
    }

    //contructor para criar
    public Habit(String description, HabitStatus done, LocalDate start, LocalDate end, Client client) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.client = client;
        this.weeks = calcEndDayForWeek(start, end);
    }

    //Contructor do update com data de ínicio
    public Habit(String id, Client client, String description, LocalDate start, LocalDate end) throws HabitExeption {
        this.id = id;
        this.description = description;
        this.done = HabitStatus.NOT_STARTED;
        this.start = start;
        this.end = end;
        this.client = client;
    }

    //Contructor do update sem data de ínicio
    public Habit(String id, Client client, String description, HabitStatus done) throws HabitExeption {
        this.id = id;
        this.description = description;
        this.done = done;
        this.client = client;
    }

    public Habit(Habit habit) {
        this.id = habit.getId();
        this.description = habit.getDescription();
        this.done = habit.getDone();
        this.start = habit.getStart();
        this.end = habit.getEnd();
        this.client = habit.getClient();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return this.description;
    }

    public HabitStatus getDone() {
        return done;
    }

    public LocalDate getStart() {
        return this.start;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public Client getClient() {
        return client;
    }

    public void update(Habit updateHabit) throws HabitExeption {
        if (updateHabit == null) {
            throw new HabitExeption(HabitsErrorEnum.HBT0002.getMessage());
        }
        if (!Objects.equals(updateHabit.description, this.description) && updateHabit.description != null) {
            this.description = updateHabit.getDescription();
        }
        if (!Objects.equals(updateHabit.getDone(), this.getDone()) && updateHabit.getDone() != null) {
            this.done = updateHabit.getDone();
        }
        
    }

    private List<Week> calcEndDayForWeek(LocalDate start, LocalDate end) {
        List<Week> weeks = new ArrayList<>();
        LocalDate startWeek = start;
        LocalDate weekEnd = startWeek.plusDays(6);
        if (weekEnd.isAfter(end)) {
            weekEnd = end;
        }
        weeks.add(new Week(startWeek, weekEnd, this));
        startWeek = weekEnd.plusDays(1);
        while (startWeek.isBefore(end) || startWeek.isEqual(end)) {
            weekEnd = startWeek.plusDays(6);
            if (weekEnd.isAfter(end)) {
                weekEnd = end;
            }
            weeks.add(new Week(startWeek, weekEnd, this));
            startWeek = startWeek.plusWeeks(1);
        }
        return weeks;
    }

    public void changeStatus(HabitStatus newStatus) {
        this.done = newStatus;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", start=" + start +
                ", end=" + end +
                ", client=" + client +
                ", weeks=" + weeks +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Habit habit)) return false;
        if (!getId().equals(habit.getId())) return false;
        if (!getDescription().equals(habit.getDescription())) return false;
        if (!getDone().equals(habit.getDone())) return false;
        if (!getStart().equals(habit.getStart())) return false;
        if (!getEnd().equals(habit.getEnd())) return false;
        if (!getClient().equals(habit.getClient())) return false;
        return getWeeks().equals(habit.getWeeks());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getDone().hashCode();
        result = 31 * result + getStart().hashCode();
        result = 31 * result + getEnd().hashCode();
        result = 31 * result + getClient().hashCode();
        result = 31 * result + getWeeks().hashCode();
        return result;
    }
}
