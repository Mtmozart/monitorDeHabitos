package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.CreateClient;
import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.FactoryClient;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final CreateClient createClient;
    private final FactoryClient factoryClient;

    public ClientController(CreateClient createClient, FactoryClient factoryClient) {
        this.createClient = createClient;
        this.factoryClient = factoryClient;
    }

    @GetMapping()
    private String  hello(){
        return "hello world";
    }
   @PostMapping()
    private Client create(@RequestBody ClientDto dto){
        Client client = factoryClient.withoutCreatedatAndUpdatedatParameters(dto.email(), dto.password(), dto.name(),
                new Address(
                        dto.addressClientDto().cep(), dto.addressClientDto().street(), dto.addressClientDto().city(),
                dto.addressClientDto().state(), dto.addressClientDto().neighborhood(), dto.addressClientDto().number(), dto.addressClientDto().complement()
                ));
        Client save = this.createClient.create(client);
        return save;

    }
}
