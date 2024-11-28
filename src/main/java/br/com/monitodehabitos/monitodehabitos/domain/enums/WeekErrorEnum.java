package br.com.monitodehabitos.monitodehabitos.domain.enums;

public enum WeekErrorEnum {
    HBT0001("Erro ao criar semana."),
    HBT0002("O valor da porcetagem não pode ser negativo."),
    HBT0003("Hábito não encontrado"),
    HBT0004("Erro ao excluir o hábito"),
    HBT0005("Erro ao listar os hábitos"),
    HBT0006("Erro ao marcar o hábito como concluído"),
    HBT0007("Erro ao alterar a data de início do hábito"),
    HBT0008("Erro ao redefinir o progresso do hábito"),
    HBT0009("Erro ao alterar a data final do hábito"),
    HBT0010("Erro ao desbloquear o hábito"),
    HBT0011("Hábito inativo"),
    HBT0012("Hábito temporariamente bloqueado"),
    HBT0013("Erro ao confirmar o hábito"),
    HBT0014("Hábito não confirmado"),
    HBT0015("Erro ao buscar o histórico de hábitos"),
    HBT0016("Semana não encontrada.");

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

