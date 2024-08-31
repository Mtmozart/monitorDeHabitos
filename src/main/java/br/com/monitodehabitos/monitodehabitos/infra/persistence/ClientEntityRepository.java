package br.com.monitodehabitos.monitodehabitos.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, UUID> {
}
