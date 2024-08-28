package br.com.monitodehabitos.monitodehabitos.domain;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Admin;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;

import java.time.LocalDateTime;

public class FactoryAdmin {
    private Admin admin;

    public Admin withAllParameters(String email, String password, String name, LocalDateTime createdAt, LocalDateTime updatedAt, Address address){
        this.admin = new Admin( email, password, name, createdAt, updatedAt, address, TypeUserEnum.ADMIN, true);
        return this.admin;
    }
}
