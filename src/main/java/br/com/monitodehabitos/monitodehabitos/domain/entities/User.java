package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.RandomPasswordResetCodeGenerator;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;
import br.com.monitodehabitos.monitodehabitos.domain.enums.UserErroEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserExeption;

import java.time.LocalDateTime;

public abstract class User {

    private String email;
    private String password;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Address address;
    private TypeUserEnum typeUserEnum;

    public User() {
    }

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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void updateAddress(Address newAddress) throws UserExeption {
        if (address == null) {
            throw new UserExeption(UserErroEnum.USR0002.getMessage());
        }

        this.address.updateAddress(newAddress);

    }


    public void resetPassword() {
        RandomPasswordResetCodeGenerator generate = new RandomPasswordResetCodeGenerator();
        this.password = generate.generateRandomCode();
    }


}
