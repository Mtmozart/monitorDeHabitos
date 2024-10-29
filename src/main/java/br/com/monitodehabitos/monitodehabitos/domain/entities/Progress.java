package br.com.monitodehabitos.monitodehabitos.domain.entities;

import java.time.LocalDate;

public class Progress {

    private Long id;
    private LocalDate currentDate;
    private Boolean completed;

    public Progress(Long id, LocalDate currentDate, Boolean completed) {
        this.id = id;
        this.currentDate = currentDate;
        this.completed = completed;
    }

    public Progress(LocalDate currentDate) {
        this.currentDate = currentDate;
        this.completed = false;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public LocalDate getDate() {
        return currentDate;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public boolean changeStatusToCompleteOrNot() {
        if(this.completed){
            return this.completed = false;
        } else  {
            return this.completed = true;
        }
    }

    @Override
    public String toString() {
        return "Progress{" +
                "currentDate=" + currentDate +
                ", completed=" + completed +
                '}';
    }
}
