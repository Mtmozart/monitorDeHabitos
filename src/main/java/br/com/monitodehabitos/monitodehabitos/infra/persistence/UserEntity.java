package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name = "user")
public abstract class UserEntity implements UserDetails {
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
    @Override
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getTypeUser().toString()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
