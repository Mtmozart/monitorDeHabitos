package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.RandomPasswordResetCodeGenerator;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class User {

    private String email;
    private String password;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Address address;
    private TypeUserEnum typeUserEnum;

    public User(String email, String password, String name, LocalDateTime createdAt, LocalDateTime updatedAt, Address address, TypeUserEnum typeUserEnum) {
        this.email = email;

        this.password = password;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.address = address;
        this.typeUserEnum = typeUserEnum;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Address getAddress() {
        return address;
    }

    public TypeUserEnum getTypeUserEnum() {
        return typeUserEnum;
    }

    public void resetPassword() {
        RandomPasswordResetCodeGenerator generate = new RandomPasswordResetCodeGenerator();
        this.password = generate.generateRandomCode();
    }
}
