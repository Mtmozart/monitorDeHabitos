package br.com.monitodehabitos.monitodehabitos.infra.controller.clientDto;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressClientResponseDto(

        String cep,
        String street,
        String city,
        String state,
        String neighborhood,
        String number,
        String complement
) {
    public AddressClientResponseDto(Address address) {
        this(
                address.getCep(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getNeighborhood(),
                address.getNumber(),
                address.getComplement()
        );
    }
}
