package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public class Client extends User {
  private Boolean isClient;

  public Client() {
    super();
  }

  public Client(String email, String password, String name, LocalDateTime createdAt, LocalDateTime updatedAt, Address address, TypeUserEnum typeUserEnum, Boolean isClient) {
    super(email, password, name, createdAt, updatedAt, address, typeUserEnum);
    this.isClient = isClient;
  }

  public Boolean getClient() {
    return isClient;
  }

  public void updateClient(Client newClient) {
    if (newClient.getEmail() != null && !newClient.getEmail().isEmpty()) {
      setEmail(newClient.getEmail());
    }
    if (newClient.getPassword() != null && !newClient.getPassword().isEmpty()) {
      setPassword(newClient.getPassword());
    }
    if (newClient.getName() != null && !newClient.getName().trim().isEmpty()) {
      setName(newClient.getName());
    }
    if (newClient.getAddress() != null) {
      updateAddress(newClient.getAddress());
    }
    setUpdatedAt(LocalDateTime.now());
  }
}
