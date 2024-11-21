package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name = "user")
public abstract class UserEntity {
    @Id
    @Column(length = 36, nullable = false)
    private String id;

    private String email;

    private String password;

    @Column(name = "full_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_user", nullable = false)
    private TypeUserEnum typeUser;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Embedded
    private AddressEntity addressEntity;

    public UserEntity() {
    }

    public UserEntity(String id, String email, String password, String name, TypeUserEnum typeUser, LocalDateTime createdAt, LocalDateTime updatedAt, AddressEntity addressEntity) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.typeUser = typeUser;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.addressEntity = addressEntity;
    }

    public String getId() {
        return id;
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

    public TypeUserEnum getTypeUser() {
        return typeUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }
}
