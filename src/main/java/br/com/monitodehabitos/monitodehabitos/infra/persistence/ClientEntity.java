package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "client")
@Table(name = "client")
public class ClientEntity extends UserEntity {

    @Column(name = "is_client", nullable = false)
    private Boolean isClient;
    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<HabitEntity> habits = new ArrayList<>();

    public ClientEntity() {
    }

    public ClientEntity(String id, String email, String password, String name, TypeUserEnum typeUser, Boolean isClient, LocalDateTime createdAt, LocalDateTime updatedAt, AddressEntity addressEntity, List<HabitEntity> habits) {
        super(id, email, password, name, typeUser, createdAt, updatedAt, addressEntity);
        this.isClient = isClient;
        this.habits = habits;
    }

    public ClientEntity(String id, String email, String password, String name, TypeUserEnum typeUser, Boolean isClient, LocalDateTime createdAt, LocalDateTime updatedAt, AddressEntity addressEntity) {
        super(id, email, password, name, typeUser, createdAt, updatedAt, addressEntity);
        this.habits = new ArrayList<>();
        this.isClient = isClient;
    }

    public List<HabitEntity> getHabits() {
        return habits;
    }


    public Boolean getClient() {
        return isClient;
    }

    public void setClient(Boolean client) {
        isClient = client;
    }



    @Override
    public String toString() {
        return "Client{" +
                "email='" + getEmail() + '\'' +
                ", name='" + getName() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                ", address=" + (getAddressEntity() != null ? getAddressEntity().toString() : "null") +
                ", typeUserEnum=" + getTypeUser() +
                ", isClient=" + isClient +
                ", habits=" + habits +
                '}';
    }

}
