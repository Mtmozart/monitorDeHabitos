package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.CreateClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.FindClient;
import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.FactoryClient;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final CreateClient createClient;
    private final FactoryClient factoryClient;
    private final FindClient findClient;

    public ClientController(CreateClient createClient, FactoryClient factoryClient, FindClient findClient) {
        this.createClient = createClient;
        this.factoryClient = factoryClient;
        this.findClient = findClient;
    }

   @PostMapping()
    private ResponseEntity create(@RequestBody ClientDto dto){
        Client client = factoryClient.withoutCreatedatAndUpdatedatParameters(dto.email(), dto.password(), dto.name(),
                new Address(
                        dto.addressClientDto().cep(), dto.addressClientDto().street(), dto.addressClientDto().city(),
                dto.addressClientDto().state(), dto.addressClientDto().neighborhood(), dto.addressClientDto().number(), dto.addressClientDto().complement()
                ));
        Client save = this.createClient.create(client);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Client> findByID(@PathVariable("id") Long id){
        System.out.println(id);
        Client client = this.findClient.findClient(id);
        return ResponseEntity.ok().body(client);
    }
}
