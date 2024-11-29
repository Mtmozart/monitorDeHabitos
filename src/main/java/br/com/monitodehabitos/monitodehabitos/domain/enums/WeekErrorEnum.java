package br.com.monitodehabitos.monitodehabitos.domain.enums;

public enum WeekErrorEnum {
    WKT0001("Erro ao criar semana."),
    WK0002("O valor da porcetagem não pode ser negativo."),
    WK0003("Erro ao deletar a semana"),
    WK0004("Semana não encontrada.");


    private String message;

    WeekErrorEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

