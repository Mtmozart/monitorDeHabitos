package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public class Admin extends User{
  private Boolean isAdmin;


  public Admin(String id, String email, String password, String name, LocalDateTime createdAt, LocalDateTime updatedAt, Address address, TypeUserEnum typeUserEnum, Boolean isAdmin) {
    super(id, email, password, name, createdAt, updatedAt, address, typeUserEnum);
    this.isAdmin = isAdmin;
  }

  public Boolean getAdmin() {
    return isAdmin;
  }
}
