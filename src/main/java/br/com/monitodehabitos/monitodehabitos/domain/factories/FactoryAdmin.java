package br.com.monitodehabitos.monitodehabitos.domain.factories;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Admin;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public class FactoryAdmin {
    private Admin admin;

    public Admin withAllParameters(String id, String email, String password, String name, LocalDateTime createdAt, LocalDateTime updatedAt, Address address){
        this.admin = new Admin(id, email, password, name, createdAt, updatedAt, address, TypeUserEnum.ADMIN, true);
        return this.admin;
    }
}
