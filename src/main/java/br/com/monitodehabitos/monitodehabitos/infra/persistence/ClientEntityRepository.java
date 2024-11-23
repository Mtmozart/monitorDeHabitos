package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, UUID> {

    @Query("SELECT c FROM client c WHERE c.email = :email")
    UserDetails findByEmail(String email);

    @Query("SELECT c FROM client c WHERE c.id = :id")
    Optional<ClientEntity> findById(String id);

    boolean existsById(String id);

    void deleteById(String id);

}
