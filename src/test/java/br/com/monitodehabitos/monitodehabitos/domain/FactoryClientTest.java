package br.com.monitodehabitos.monitodehabitos.domain;

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
                "email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(),
                null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));
        ;

        Assertions.assertEquals("Matheus Mozart da Silva Neves Borges", client.getName());
        Assertions.assertEquals(TypeUserEnum.CLIENT, client.getTypeUserEnum());
    }
    @Test
    void deveriaCriarUsuarioComFabricaSemDataDeCriacaoEAtualizazao() {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withoutCreatedatAndUpdatedatParameters(
                "email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));
        ;

        Assertions.assertEquals("Matheus Mozart da Silva Neves Borges", client.getName());
        Assertions.assertEquals(TypeUserEnum.CLIENT, client.getTypeUserEnum());
    }
}