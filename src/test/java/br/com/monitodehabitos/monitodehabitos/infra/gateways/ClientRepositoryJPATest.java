package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserException;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientRepositoryJPATest {
    @Mock
    private ClientEntityRepository clientEntityRepository;
    @Mock
    private ClientEntityMapper clientEntityMapper;
    @Mock
    private Client client;
    @Mock
    ClientEntity clientEntity;
    @Mock
    private Address address;
    @InjectMocks
    private ClientRepositoryJPA clientRepositoryJPA;

    @Test
    void save_shouldConvertAndSaveClientEntity() {
        // Arrange
        Address address = new Address(
                "12345-678",
                "Rua dos Programadores",
                "São Paulo",
                "SP",
                "Bairro Central",
                "123",
                "Apto 45"
        );

        Client client = new Client(
                1L,
                "cliente@example.com",
                "senhaSegura123",
                "Carlos Silva",
                LocalDateTime.now(),
                LocalDateTime.now(),
                address,
                TypeUserEnum.CLIENT,
                true
        );

        ClientEntity clientEntity = new ClientEntity();
        when(clientEntityMapper.toClientEntity(client)).thenReturn(clientEntity);
        when(clientEntityRepository.save(clientEntity)).thenReturn(clientEntity);
        when(clientEntityMapper.toClientDomain(clientEntity)).thenReturn(client);

        // Act
        Client result = clientRepositoryJPA.save(client);

        // Assert
        assertEquals(client, result);
        verify(clientEntityMapper, times(1)).toClientEntity(client);
        verify(clientEntityRepository, times(1)).save(clientEntity);
        verify(clientEntityMapper, times(1)).toClientDomain(clientEntity);
    }

    @Test
    void update_whenClientExists_shouldUpdateAndReturnClient() throws UserException {
        Long clientId = 1L;

        Address originalAddress = new Address(
                "12345-678",
                "Rua dos Programadores",
                "São Paulo",
                "SP",
                "Bairro Central",
                "123",
                "Apto 45"
        );

        Address updatedAddress = new Address(
                "98765-432",
                "Rua dos Desenvolvedores",
                "São Paulo",
                "SP",
                "Bairro Novo",
                "456",
                "Apto 67"
        );

        Client originalClient = new Client(
                1l,
                "cliente@example.com",
                "senhaSegura123",
                "Carlos Silva",
                LocalDateTime.now(),
                LocalDateTime.now(),
                originalAddress,
                TypeUserEnum.CLIENT,
                true
        );

        Client updateClient = new Client(
                1L,
                "cliente@example.com",
                "novaSenhaSegura123",
                "Carlos Silva",
                LocalDateTime.now(),
                LocalDateTime.now(),
                updatedAddress,
                TypeUserEnum.CLIENT,
                false
        );

        ClientEntity originalClientEntity = new ClientEntity(); // Suponha que ClientEntity tenha um construtor padrão
        ClientEntity updatedClientEntity = new ClientEntity(); // Suponha que ClientEntity tenha um construtor padrão

        when(clientEntityRepository.findById(clientId)).thenReturn(Optional.of(originalClientEntity));
        when(clientEntityMapper.toClientDomain(originalClientEntity)).thenReturn(originalClient);
        when(clientEntityMapper.toClientEntityUpdate(clientId, originalClient)).thenReturn(updatedClientEntity);
        when(clientEntityRepository.save(updatedClientEntity)).thenReturn(updatedClientEntity);

        // Act
        Client result = clientRepositoryJPA.update(clientId, updateClient);

        // Assert
        assertEquals(updateClient.getEmail(), result.getEmail());
        assertEquals(updateClient.getAddress().getStreet(), result.getAddress().getStreet());
        verify(clientEntityRepository, times(1)).findById(clientId);
        verify(clientEntityMapper, times(1)).toClientDomain(originalClientEntity);
        verify(clientEntityMapper, times(1)).toClientEntityUpdate(clientId, originalClient);
        verify(clientEntityRepository, times(1)).save(updatedClientEntity);
    }

    @Test
    void update_whenClientDoesNotExist_shouldThrowException() {
        Long clientId = 1L;
        Client updateClient = new Client(
                1L,
                "cliente@example.com",
                "novaSenhaSegura123",
                "Carlos Silva",
                LocalDateTime.now(),
                LocalDateTime.now(),
                new Address(
                        "98765-432",
                        "Rua dos Desenvolvedores",
                        "São Paulo",
                        "SP",
                        "Bairro Novo",
                        "456",
                        "Apto 67"
                ),
                TypeUserEnum.CLIENT,
                false
        );

        // Arrange
        when(clientEntityRepository.findById(clientId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> clientRepositoryJPA.update(clientId, updateClient),
                "Usuário não encontrado");

        verify(clientEntityRepository, times(1)).findById(clientId);
        verify(clientEntityMapper, never()).toClientDomain(any());
        verify(clientEntityMapper, never()).toClientEntityUpdate(anyLong(), any());
        verify(clientEntityRepository, never()).save(any());
    }

    @Test
    void findById() {
        Long clientId = 1L;
        when(clientEntityRepository.findById(clientId)).thenReturn(Optional.of(clientEntity));
        Optional<ClientEntity> foundClient = clientEntityRepository.findById(clientId);
        assertNotNull(foundClient);
        verify(clientEntityRepository, times(1)).findById(clientId);
    }

    @Test
    void delete_whenClientExists_shouldCallDeleteById() {
        Long clientId = 1L;
        when(clientEntityRepository.existsById(clientId)).thenReturn(true);

        clientRepositoryJPA.delete(clientId);

        verify(clientEntityRepository, times(1)).deleteById(clientId);
    }

    @Test
    void delete_whenClientDoesNotExist_shouldThrowException() {
        Long clientId = 1L;
        when(clientEntityRepository.existsById(clientId)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> clientRepositoryJPA.delete(clientId),
                "Usuário não encontrado para exclusão");
        verify(clientEntityRepository, never()).deleteById(clientId);
    }

}

