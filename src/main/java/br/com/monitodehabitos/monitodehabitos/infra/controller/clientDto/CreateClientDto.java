package br.com.monitodehabitos.monitodehabitos.infra.controller.clientDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateClientDto(
        @NotBlank(message = "email.obrigatorio") @Email(message = "{email.invalid}")
        String email,
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "{password.invalid}")
        String password,
        @NotBlank(message = "{name.required}")
        String name,
        @NotNull(message = "{address.required}") @Valid
        AddressClientDto addressClientDto
) {
}
