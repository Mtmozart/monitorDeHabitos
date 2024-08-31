package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private TypeUserEnum typeUser;
    private Boolean isClient;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Embedded
    private AddressEntity addressEntity;

    public ClientEntity() {
    }

    public ClientEntity(Long id, String email, String password, String name, TypeUserEnum typeUser, Boolean isClient, LocalDateTime createdAt, LocalDateTime updatedAt, AddressEntity addressEntity) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.typeUser = typeUser;
        this.isClient = isClient;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.addressEntity = addressEntity;
    }


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeUserEnum getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUserEnum typeUser) {
        this.typeUser = typeUser;
    }

    public Boolean getClient() {
        return isClient;
    }

    public void setClient(Boolean client) {
        isClient = client;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }
}
