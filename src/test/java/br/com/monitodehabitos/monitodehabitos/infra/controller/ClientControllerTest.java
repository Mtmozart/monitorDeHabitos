package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.gateway.ClientRepository;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClientRepository clientRepository;
    @MockBean
    private FactoryClient factoryClient;


    @Test
    @DisplayName("Should give back status 201, created.")
    void scenario01() throws Exception {
        //Arrange
        String json =
                """
                                {
                                  "email": "cliente@exemplo.com",
                                  "password": "senhaSegura123",
                                  "name": "João da Silva",
                                  "addressClientDto": {
                                    "cep": "01001-000",
                                    "street": "Rua da Alegria",
                                    "city": "São Paulo",
                                    "state": "SP",
                                    "neighborhood": "Centro",
                                    "number": "123",
                                    "complement": "Apto 45"
                                  }
                                }
                        """;
        //Act
        var response = mockMvc.perform(
                post("/client")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        assertEquals(201, response.getStatus());
    }

    @Test
    @DisplayName("Should give back status 200 to findClient.")
    void scenario02() throws Exception {

        //Arrange
        Long id = 1L;
        //Act
        var response = mockMvc.perform(
                get("/client/{id}", id)
        ).andReturn().getResponse();

        //Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Should give back status 204 to delete client - no content.")
    void scenario03() throws Exception {

        //Arrange
        Long id = 1L;
        //Act
        var response = mockMvc.perform(
                delete("/client/1", id)
        ).andReturn().getResponse();

        //Assert
        assertEquals(204, response.getStatus());
    }

    @Test
    @DisplayName("Should give back status 200 when updating a client successfully.")
    void scenario04() throws Exception {

        // Arrange
        Long id = 1L;
        //Arrange
        String json =
                """
                                {
                                  "email": "client-atualizado@exemplo.com"
                                
                                }
                        """;

        // Act
        var response = mockMvc.perform(
                put("/client/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andReturn().getResponse();

        // Assert
        assertEquals(200, response.getStatus());
    }

}