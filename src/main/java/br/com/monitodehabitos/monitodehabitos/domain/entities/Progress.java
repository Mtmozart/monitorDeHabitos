package br.com.monitodehabitos.monitodehabitos.domain.entities;

import java.time.LocalDate;

public class Progress {
    private LocalDate currentDate;
    private Boolean completed;

    public Progress(LocalDate currentDate, Boolean completed) {
        this.currentDate = currentDate;
        this.completed = completed;
    }

    public Progress(LocalDate currentDate) {
        this.currentDate = currentDate;
        this.completed = false;
    }

    public LocalDate getDate() {
        return currentDate;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void addPercentage(LocalDate currentDate){
        this.currentDate = currentDate;
        this.completed = false;
    }

}
