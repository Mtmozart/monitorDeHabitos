package br.com.monitodehabitos.monitodehabitos.domain.exception;

public class HabitExeption extends Exception  {
    private String code;

    public HabitExeption(String message) {
        super(message);
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}

