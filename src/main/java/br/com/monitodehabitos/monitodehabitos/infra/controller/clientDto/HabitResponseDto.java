package br.com.monitodehabitos.monitodehabitos.infra.controller.clientDto;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record HabitResponseDto(
        String id,
        String email,
        String name,
        AddressClientResponseDto address,
        LocalDateTime createdAt,
        LocalDateTime updateAt
) {

    public HabitResponseDto(Client client) {
        this(
                client.getId(),
                client.getEmail(),
                client.getName(),
                new AddressClientResponseDto(client.getAddress()),
                client.getCreatedAt(),
                client.getUpdatedAt()
        );
    }
}
