package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.CreateClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.DeleteClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.FindClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.UpdateClient;
import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserExeption;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryClient;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.monitodehabitos.monitodehabitos.infra.utils.Utilities.log;


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
    private ResponseEntity create(@RequestBody CreateClientDto dto) {
        log.info("Start the create client::ClientController");
        Client client = factoryClient.withoutCreatedatAndUpdatedatParameters(dto.email(), dto.password(), dto.name(),
                new Address(
                        dto.addressClientDto().cep(), dto.addressClientDto().street(), dto.addressClientDto().city(),
                        dto.addressClientDto().state(), dto.addressClientDto().neighborhood(), dto.addressClientDto().number(), dto.addressClientDto().complement()
                ));
        this.createClient.create(client);
        log.info("End the create client::ClientController");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Client> findByID(@PathVariable("id") Long id) {
        log.info("Start find the client::ClientController");
        Client client = this.findClient.findClient(id);
        log.info("End find the client::ClientController");
        return ResponseEntity.ok().body(client);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity delete(@PathVariable("id") Long id) {
        log.info("Start delete the client::ClientController");
        this.deleteClient.delete(id);
        log.info("End delete the client::ClientController");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity update(@PathVariable("id") Long id, @RequestBody UpdateClientDto dto) throws UserExeption {
        log.info("Start update the client::ClientController");
        Client updatesClient = factoryClient.updateClient(dto.email(), dto.password(), dto.name(),
                new Address(
                        dto.cep(), dto.street(), dto.city(),
                        dto.state(), dto.neighborhood(), dto.number(), dto.complement()
                ));
        this.updateClient.update(id, updatesClient);
        log.info("End update the client::ClientController");
        return ResponseEntity.ok(new ResponseDto("Atualização realizada com sucesso."));
    }

}
