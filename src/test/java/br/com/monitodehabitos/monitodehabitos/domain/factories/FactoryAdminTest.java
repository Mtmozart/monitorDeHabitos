package br.com.monitodehabitos.monitodehabitos.domain.factories;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Admin;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryAdmin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class FactoryAdminTest {


    @Test
    void deveriaCriarUsuarioComFabrica() {
        FactoryAdmin factoryAdmin = new FactoryAdmin();
        Admin admin = factoryAdmin.withAllParameters(
                1l, "email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(),
                null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));
        ;

        Assertions.assertEquals("Matheus Mozart da Silva Neves Borges", admin.getName());
        Assertions.assertEquals(TypeUserEnum.ADMIN, admin.getTypeUserEnum());
    }
}