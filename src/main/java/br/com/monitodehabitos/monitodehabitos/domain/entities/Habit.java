package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Habit {
    private Long id;
    private String description;
    private Boolean done;
    private LocalDate start;
    private LocalDate end;
    private Client client;
    private List<Week> weeks = new ArrayList<>();

    public Habit(Long id, String description, Boolean done, LocalDate start, LocalDate end, Client client, List<Week> week) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.start = start;
        this.end = end;
        this.client = client;
        this.weeks = week;
    }

    //Contructor do update com data de ínicio
    public Habit(Long id, Client client, String description, LocalDate start, LocalDate end) throws HabitExeption {
        this.id = id;
        this.description = description;
        this.done = false;
        this.start = start;
        this.end = end;
        this.client = client;
    }

    //Contructor do update sem data de ínicio
    public Habit(Long id, Client client, String description) throws HabitExeption {
        this.id = id;
        this.description = description;
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

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getDone() {
        return this.done;
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

//    public void update(Habit updateHabit) throws HabitExeption {
//        if (updateHabit == null) {
//            throw new HabitExeption(HabitsErrorEnum.HBT0002.getMessage());
//        }
//        if (updateHabit.start != this.getStart() && updateHabit.start != null) {
//            this.changeDateStart(updateHabit.getStart());
//            this.getProgress().clear();
//            updateHabit.getProgress().stream()
//                    .map(p -> this.getProgress().add(p))
//                    .collect(Collectors.toList());
//        }
//        if (!Objects.equals(updateHabit.description, this.description) && updateHabit.description != null) {
//            this.description = updateHabit.getDescription();
//        }
//    }

//    public boolean changeDo(LocalDate date) throws HabitExeption, WeekException {
//        if (date == null) {
//            throw new HabitExeption(HabitsErrorEnum.HBT0016.getMessage());
//        }
//        boolean isCompleted = this.progress.stream()
//                .filter(n -> date.equals(n.getDate()))
//                .findFirst()
//                .map(Progress::changeStatusToCompleteOrNot)
//                .orElse(false);
//
//        this.done = this.progress.stream().allMatch(Progress::getCompleted);
//        return isCompleted;
//    }

//    private List<Week> calcWeek(LocalDate start, LocalDate end){
//        Week week = new Week(start, end);
//
//    }

    private LocalDate calcEndDayForWeek(LocalDate start) {
        DayOfWeek currentDay = start.getDayOfWeek();

        int daysUntilSaturday = DayOfWeek.SATURDAY.getValue() - currentDay.getValue();

        if (daysUntilSaturday <= 0) {
            daysUntilSaturday += 7;
        }
        return start.plusDays(daysUntilSaturday);
    }

    public LocalDate calcEnd(LocalDate start) throws HabitExeption {
        if (start != null) {
            return start.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        }
        this.end = this.start;
        return this.end;
    }




    public Client getClient() {
        return client;
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
