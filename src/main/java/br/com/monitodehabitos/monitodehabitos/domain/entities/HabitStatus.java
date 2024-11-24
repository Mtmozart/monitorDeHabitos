package br.com.monitodehabitos.monitodehabitos.domain.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum HabitStatus {
    NOT_STARTED("not_staterd"),
    IN_PROGRESS("in_progress"),
    COMPLETED("completed");

    private String status;

    HabitStatus(String status){
        this.status = status;
    }

    @JsonCreator
    public static HabitStatus fromString(String text){
        for(HabitStatus habit : HabitStatus.values()){
            if(habit.status.equalsIgnoreCase(text)){
                return habit;
            }
        }
        throw new IllegalArgumentException("Nenhum status encontradp para a string fornecida: " + text);
    }
}
