package br.com.monitodehabitos.monitodehabitos.domain.enums;

public enum UserErroEnum {
    USR0001("Erro ao criar o usuário"),
    USR0002("Erro ao atualizar o usuário"),
    USR0003("Usuário não encontrado"),
    USR0004("Erro ao excluir o usuário"),
    USR0005("Nome de usuário já em uso"),
    USR0006("E-mail já cadastrado ou inválido"),
    USR0007("Senha inválida: a senha deve ter no mínimo 8 caracteres, letras maiúsculas e minúsculas, um número e um caractere especial."),
    USR0008("Erro ao resetar a senha"),
    USR0009("Erro ao bloquear o usuário"),
    USR0010("Erro ao desbloquear o usuário"),
    USR0011("Usuário inativo"),
    USR0012("Usuário bloqueado temporariamente"),
    USR0013("Erro ao confirmar o usuário"),
    USR0014("Usuário não confirmado"),
    USR0015("Senha ou e-mail inválidos");

    private String message;

    UserErroEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
