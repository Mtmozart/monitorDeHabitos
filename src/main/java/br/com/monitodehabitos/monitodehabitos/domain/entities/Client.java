package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public class Client extends User{
  private Boolean isClient;


  public Client(String email, String password, String name, LocalDateTime createdAt, LocalDateTime updatedAt, Address address, TypeUserEnum typeUserEnum, Boolean isClient) {
    super( email, password, name, createdAt, updatedAt, address, typeUserEnum);
    this.isClient = isClient;
  }

  public Boolean getClient() {
    return isClient;
  }
}
