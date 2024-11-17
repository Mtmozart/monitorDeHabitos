package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserException;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryClient;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

class ClientTest {

    @Test
    void deveriaAtualizarClienteComMetodoUpdate() throws UserException {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters(
                1L,
                "email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(),
                null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));

        Address newAddress = new Address(
                "76820-125",
                "nova rua",
                "São Paulo",
                "SP",
                "Novo Bairro",
                "123",
                "Apto 45"
        );

        Client updatedClient = new Client(
                1L,
                "email-atualizado@gmail.com",
                "Password@123",
                "Matheus Mozart Borges",
                null,
                null,
                newAddress,
                TypeUserEnum.CLIENT,
                null
        );

        client.updateClient(updatedClient);

        Assertions.assertEquals("email-atualizado@gmail.com", client.getEmail());
        Assertions.assertEquals("Password@123", client.getPassword());
        Assertions.assertEquals("Matheus Mozart Borges", client.getName());
        Assertions.assertEquals(newAddress.getCep(), client.getAddress().getCep());
        Assertions.assertEquals(newAddress.getStreet(), client.getAddress().getStreet());
        Assertions.assertEquals(newAddress.getCity(), client.getAddress().getCity());
        Assertions.assertEquals(newAddress.getState(), client.getAddress().getState());
        Assertions.assertEquals(newAddress.getNeighborhood(), client.getAddress().getNeighborhood());
        Assertions.assertEquals(newAddress.getNumber(), client.getAddress().getNumber());
        Assertions.assertEquals(newAddress.getComplement(), client.getAddress().getComplement());
    }

    @Test
    void deveriaAtualizarNome() throws UserException {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters(
                1L,
                "email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(),
                null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));

        Client updatedClient = new Client(
                null,
                null,
                null,
                "Novo Nome",
                null,
                null,
                null,
                null,
                null
        );

        client.updateClient(updatedClient);

        Assertions.assertEquals("Novo Nome", client.getName());
    }

    @Test
    void deveriaAtualizarSenha() throws UserException {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters(
                1L,
                "email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(),
                null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));

        Client updatedClient = new Client(
                null,
                null,
                "NovaSenha@123",
                null,
                null,
                null,
                null,
                null,
                null
        );
        client.updateClient(updatedClient);

        Assertions.assertEquals("NovaSenha@123", client.getPassword());
    }

    @Test
    void deveriaAtualizarEndereco() throws UserException {
        // Arrange
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters(
                1L,
                "email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(),
                null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));

        // Novo endereço para atualizar
        Address newAddress = new Address(
                "76820-125",
                "nova rua",
                "São Paulo",
                "SP",
                "Novo Bairro",
                "123",
                "Apto 45"
        );

        // Atualiza o cliente com o novo endereço
        Client updatedClient = new Client(
                null,
                null,    // Não altera o email
                null,    // Não altera a senha
                null,    // Não altera o nome
                null,    // Não altera o `createdAt`
                null,    // Não altera o `updatedAt`
                newAddress,
                null,    // Não altera o `isClient`
                null     // Não altera o `isClient`
        );

        client.updateClient(updatedClient);

        // Assert
        Address clientAddress = client.getAddress();
        Assertions.assertEquals("76820-125", clientAddress.getCep());
        Assertions.assertEquals("nova rua", clientAddress.getStreet());
        Assertions.assertEquals("São Paulo", clientAddress.getCity());
        Assertions.assertEquals("SP", clientAddress.getState());
        Assertions.assertEquals("Novo Bairro", clientAddress.getNeighborhood());
        Assertions.assertEquals("123", clientAddress.getNumber());
        Assertions.assertEquals("Apto 45", clientAddress.getComplement());
    }

    @Test
    @DisplayName("Should be to return all habits")
    void getHabits() throws HabitExeption {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters(
                1L,
                "email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(),
                null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));

        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "description", LocalDate.now(), LocalDate.now());
        Habit habit2 = factoryHabit.withDescriptionAndDate(1L, client, "description", LocalDate.now(), LocalDate.now());
        Habit habit3 = factoryHabit.withDescriptionAndDate(1L, client, "description", LocalDate.now(), LocalDate.now());
        client.addHabit(habit);
        client.addHabit(habit2);
        client.addHabit(habit3);
        Assertions.assertEquals(3, client.getHabits().size());
    }

    @Test
    @DisplayName("Should be to add habit")
    void addHabit() throws HabitExeption {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters(
                1L,
                "email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(),
                null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "description", LocalDate.now(), LocalDate.now());
        client.addHabit(habit);

        Assertions.assertEquals("description", client.getHabits().get(0).getDescription());
        Assertions.assertEquals(1L, client.getHabits().get(0).getId());
    }

    @Test
    @DisplayName("Should be to remove habit")
    void removeHabit() throws HabitExeption {
        FactoryClient factoryClient = new FactoryClient();
        Client client = factoryClient.withAllParameters(
                1L,
                "email@gmail.com", "Bor@5930", "Matheus Mozart da Silva Neves Borges", LocalDateTime.now(),
                null, new Address("76820-124", "rua miguel chakian", "Porto Velho", "RO", "Nova Porto Velho", "848", null));
        FactoryHabit factoryHabit = new FactoryHabit();
        Habit habit = factoryHabit.withDescriptionAndDate(1L, client, "description", LocalDate.now(), LocalDate.now());
        client.addHabit(habit);
        client.removeHabit(habit);
        Assertions.assertEquals(0, client.getHabits().size());

    }
}