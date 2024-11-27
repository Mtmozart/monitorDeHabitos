package br.com.monitodehabitos.monitodehabitos.domain.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Progress {

    private String id;
    private LocalDate currentDate;
    private ProgressEnumStatus status;
    private Week week;

    public Progress(String id, LocalDate currentDate, ProgressEnumStatus status, Week week) {
        this.id = id;
        this.currentDate = currentDate;
        this.status = status;
        this.week = week;
    }

    //create
    public Progress(LocalDate currentDate, ProgressEnumStatus status, Week week) {
        this.id = UUID.randomUUID().toString();
        this.currentDate = currentDate;
        this.status = status;
        this.week = week;
    }


    public String getId() {
        return id;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public LocalDate getDate() {
        return currentDate;
    }

    public ProgressEnumStatus getProgressEnumStatus() {
        return status;
    }

    public ProgressEnumStatus getStatus() {
        return status;
    }

    public Week getWeek() {
        return week;
    }

    public ProgressEnumStatus changeStatusToCompleteOrNot() {
        if(this.status.equals(ProgressEnumStatus.NOT_STARTED)){
            return this.status = ProgressEnumStatus.COMPLETED;
        } else  {
            return this.status = ProgressEnumStatus.NOT_STARTED;
        }
    }

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", currentDate=" + currentDate +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Progress progress)) return false;

        if (!getId().equals(progress.getId())) return false;
        if (!getCurrentDate().equals(progress.getCurrentDate())) return false;
        return status == progress.status;
    }
    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getCurrentDate().hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }



}
