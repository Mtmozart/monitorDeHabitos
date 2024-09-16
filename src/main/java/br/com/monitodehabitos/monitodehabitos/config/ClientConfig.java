package br.com.monitodehabitos.monitodehabitos.config;


import br.com.monitodehabitos.monitodehabitos.application.gateway.ClientRepository;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.*;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryClient;
import br.com.monitodehabitos.monitodehabitos.infra.gateways.ClientEntityMapper;
import br.com.monitodehabitos.monitodehabitos.infra.gateways.ClientRepositoryJPA;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    CreateClient createClient(ClientRepository clientRepository) {
        return new CreateClient(clientRepository);
    }

    @Bean
    FindClient findById(ClientRepository clientRepository) {
        return new FindClient(clientRepository);
    }

    @Bean
    DeleteClient deleteClient(ClientRepository clientRepository) {
        return new DeleteClient(clientRepository);
    }

    @Bean
    UpdateClient updateClient(ClientRepository clientRepository) {
        return new UpdateClient(clientRepository);
    }

    @Bean
    ClientRepositoryJPA clientRepositoryJPA(ClientEntityRepository clientEntityRepository, ClientEntityMapper clientEntityMapper) {
        return new ClientRepositoryJPA(clientEntityRepository, clientEntityMapper);
    }

    @Bean
    FactoryClient factoryClient() {
        return new FactoryClient();
    }

    @Bean
    ClientEntityMapper clientEntityMapper() {
        return new ClientEntityMapper();
    }

}
