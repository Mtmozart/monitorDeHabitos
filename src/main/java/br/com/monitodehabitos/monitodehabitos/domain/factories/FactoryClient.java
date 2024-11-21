package br.com.monitodehabitos.monitodehabitos.domain.factories;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public class FactoryClient {
    private Client client;
    /*fábrica para criar o usuário, não precisa iniciar com hábtios*/
    public Client withAllParameters(String id, String email, String password, String name, LocalDateTime createdAt, LocalDateTime updatedAt, Address address) {
        this.client = new Client(id, email, password, name, createdAt, updatedAt, address, TypeUserEnum.CLIENT, true, null);
        return this.client;
    }

    public Client withoutCreatedatAndUpdatedatParameters(String email, String password, String name, Address address) {
        this.client = new Client(email, password, name, LocalDateTime.now(), null, address, TypeUserEnum.CLIENT, true);
        return this.client;
    }

    public Client updateClient(String email, String password, String name, Address address) {
        Client newClient = new Client();

        if (email != null) {
            newClient.setEmail(email);
        }
        if (password != null) {
            newClient.setPassword(password);
        }
        if (name != null) {
            newClient.setName(name);
        }

        if (address != null) {
            Address newAddress = new Address();
            if (address.getCep() != null) {
                newAddress.setCep(address.getCep());
            }
            if (address.getStreet() != null) {
                newAddress.setStreet(address.getStreet());
            }
            if (address.getCity() != null) {
                newAddress.setCity(address.getCity());
            }
            if (address.getState() != null) {
                newAddress.setState(address.getState());
            }
            if (address.getNeighborhood() != null) {
                newAddress.setNeighborhood(address.getNeighborhood());
            }
            if (address.getNumber() != null) {
                newAddress.setNumber(address.getNumber());
            }
            if (address.getComplement() != null) {
                newAddress.setComplement(address.getComplement());
            }
            newClient.setAddress(newAddress);
        }

        return newClient;
    }
}
