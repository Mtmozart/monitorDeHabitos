package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.CreateClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.DeleteClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.FindClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.UpdateClient;
import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserException;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final CreateClient createClient;
    private final FactoryClient factoryClient;
    private final FindClient findClient;
    private final DeleteClient deleteClient;
    private final UpdateClient updateClient;

    public ClientController(CreateClient createClient, FactoryClient factoryClient, FindClient findClient, DeleteClient deleteClient, UpdateClient updateClient) {
        this.createClient = createClient;
        this.factoryClient = factoryClient;
        this.findClient = findClient;
        this.deleteClient = deleteClient;
        this.updateClient = updateClient;
    }

   @PostMapping()
    private ResponseEntity create(@RequestBody CreateClientDto dto){
        Client client = factoryClient.withoutCreatedatAndUpdatedatParameters(null, dto.email(), dto.password(), dto.name(),
                new Address(
                        dto.addressClientDto().cep(), dto.addressClientDto().street(), dto.addressClientDto().city(),
                dto.addressClientDto().state(), dto.addressClientDto().neighborhood(), dto.addressClientDto().number(), dto.addressClientDto().complement()
                ));
        this.createClient.create(client);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Client> findByID(@PathVariable("id") Long id){
        Client client = this.findClient.findClient(id);
        return ResponseEntity.ok().body(client);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity delete(@PathVariable("id") Long id){
       this.deleteClient.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity update(@PathVariable("id") Long id, @RequestBody UpdateClientDto dto) throws UserException {
        Client updatesClient = factoryClient.updateClient(dto.email(), dto.password(), dto.name(),
                new Address(
                        dto.cep(), dto.street(), dto.city(),
                        dto.state(), dto.neighborhood(), dto.number(), dto.complement()
                ));
        this.updateClient.update(id, updatesClient);

        return ResponseEntity.ok(new ResponseDto("Atualização realizada com sucesso."));
    }

}
