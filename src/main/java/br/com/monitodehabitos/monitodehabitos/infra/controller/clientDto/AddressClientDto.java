package br.com.monitodehabitos.monitodehabitos.infra.controller.clientDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressClientDto (
        @NotBlank(message = "{cep.required}")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "{cep.invalid}")
        String cep,

        @NotBlank(message = "{street.required}")
        String street,

        @NotBlank(message = "{city.required}")
        String city,

        @NotBlank(message = "{state.required}")
        @Size(min = 2, max = 2, message = "{state.invalid}")
        String state,

        @NotBlank(message = "{neighborhood.required}")
        String neighborhood,

        @NotNull(message = "{number.required}")
        String number,

        String complement
){}
