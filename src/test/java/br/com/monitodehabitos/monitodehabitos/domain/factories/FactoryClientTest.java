package br.com.monitodehabitos.monitodehabitos.domain.factories;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class FactoryClientTest {
    @Test
    void deveriaCriarUsuarioComFabrica() {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters(
                1L, "email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(),
                null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));
        ;

        Assertions.assertEquals("Matheus Mozart da Silva Neves Borges", client.getName());
        Assertions.assertEquals(TypeUserEnum.CLIENT, client.getTypeUserEnum());
    }
    @Test
    void deveriaCriarUsuarioComFabricaSemDataDeCriacaoEAtualizazao() {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withoutCreatedatAndUpdatedatParameters(
                1L,
                "email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));
        ;
        Assertions.assertEquals("Matheus Mozart da Silva Neves Borges", client.getName());
        Assertions.assertEquals(TypeUserEnum.CLIENT, client.getTypeUserEnum());
    }
}