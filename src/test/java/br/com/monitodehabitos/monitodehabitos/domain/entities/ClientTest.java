package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserExeption;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryClient;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    @DisplayName("Should be to update")
    void scenario01() throws UserExeption {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters("email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(), null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));

        Address newAddress = new Address("76820-125", "nova rua", "São Paulo", "SP", "Novo Bairro", "123", "Apto 45");

        Client updatedClient = new Client("email-atualizado@gmail.com", "Password@123", "Matheus Mozart Borges", null, null, newAddress, TypeUserEnum.CLIENT, null);

        client.updateClient(updatedClient);

        assertEquals("email-atualizado@gmail.com", client.getEmail());
        assertEquals("Password@123", client.getPassword());
        assertEquals("Matheus Mozart Borges", client.getName());
        assertEquals(newAddress.getCep(), client.getAddress().getCep());
        assertEquals(newAddress.getStreet(), client.getAddress().getStreet());
        assertEquals(newAddress.getCity(), client.getAddress().getCity());
        assertEquals(newAddress.getState(), client.getAddress().getState());
        assertEquals(newAddress.getNeighborhood(), client.getAddress().getNeighborhood());
        assertEquals(newAddress.getNumber(), client.getAddress().getNumber());
        assertEquals(newAddress.getComplement(), client.getAddress().getComplement());
    }

    @Test
    @DisplayName("Should be update the name")
    void secnario02() throws UserExeption {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters("email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(), null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));

        Client updatedClient = new Client(null, null, "Novo Nome", null, null, null, null, null);

        client.updateClient(updatedClient);

        assertEquals("Novo Nome", client.getName());
    }

    @Test
    void deveriaAtualizarSenha() throws UserExeption {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters("email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(), null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));

        Client updatedClient = new Client(null, "NovaSenha@123", null, null, null, null, null, null);
        client.updateClient(updatedClient);

        assertEquals("NovaSenha@123", client.getPassword());
    }

    @Test
    void deveriaAtualizarEndereco() throws UserExeption {
        // Arrange
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters("email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(), null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));

        // Novo endereço para atualizar
        Address newAddress = new Address("76820-125", "nova rua", "São Paulo", "SP", "Novo Bairro", "123", "Apto 45");

        // Atualiza o cliente com o novo endereço
        Client updatedClient = new Client(null,    // Não altera o email
                null,    // Não altera a senha
                null,    // Não altera o nome
                null,    // Não altera o `createdAt`
                null,    // Não altera o `updatedAt`
                newAddress, null,    // Não altera o `isClient`
                null     // Não altera o `isClient`
        );

        client.updateClient(updatedClient);

        // Assert
        Address clientAddress = client.getAddress();
        assertEquals("76820-125", clientAddress.getCep());
        assertEquals("nova rua", clientAddress.getStreet());
        assertEquals("São Paulo", clientAddress.getCity());
        assertEquals("SP", clientAddress.getState());
        assertEquals("Novo Bairro", clientAddress.getNeighborhood());
        assertEquals("123", clientAddress.getNumber());
        assertEquals("Apto 45", clientAddress.getComplement());
    }


    @Test
    @DisplayName("Should be reset the password")
    void scenario03() {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters("email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(), null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));
        client.resetPassword();

        Assertions.assertNotEquals("Bor@5930", client.getPassword());

    }

    @Test
    @DisplayName("Should be to return habits")
    void scenario04() throws HabitExeption {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters("email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(), null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(client, "Descrição genéria de algo", LocalDate.now());
        Habit habit2 = factoryHabit.withDescriptionAndDate(client, "Descrição genéria de algo 2", LocalDate.now());
        client.addHabit(habit);
        client.addHabit(habit2);

        assertEquals(2, client.getHabits().size(), "A lista de hábitos deve conter 2 hábitos");
        assertTrue(client.getHabits().contains(habit), "O hábito 1 deve estar presente na lista");
        assertTrue(client.getHabits().contains(habit2), "O hábito 2 deve estar presente na lista");
    }

    @Test
    @DisplayName("Should throw HabitException when adding an invalid habit")
    void scneario05() {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters("email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(), null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));

        assertThrows(HabitExeption.class, () -> {
            client.addHabit(null);
        });
    }

    @Test
    @DisplayName("Should add habit to client")
    void scneraio06() throws HabitExeption {
        // Arrange - configurar os objetos
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters(
                "email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges",
                LocalDateTime.now(), null,
                new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null)
        );
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(client, "Descrição genérica de algo", LocalDate.now());
        client.addHabit(habit);
        assertEquals(1, client.getHabits().size(), "A lista de hábitos deve conter 1 hábito");
        assertEquals(client.getName(),habit.getClient().getName());
    }


    @Test
    @DisplayName("Should be to remove habits")
    void scenario07() throws HabitExeption {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters(
                "email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges",
                LocalDateTime.now(), null,
                new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null)
        );
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(client, "Descrição genérica de algo", LocalDate.now());
        client.addHabit(habit);
        client.removeHabit(habit);
        assertEquals(0, client.getHabits().size(), "A lista de hábitos deve conter 1 hábito");
        assertEquals(null, habit.getClient());

    }
}