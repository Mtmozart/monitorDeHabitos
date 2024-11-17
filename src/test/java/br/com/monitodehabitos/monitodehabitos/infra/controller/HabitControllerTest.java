package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.FindClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.*;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Progress;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HabitControllerTest {
    @MockBean
    private CreateHabit createHabit;
    @MockBean
    private FindClient findClient;
    @MockBean
    private FindHabit findHabit;
    @MockBean
    private DeleteHabit deleteHabit;
    @MockBean
    private FindAllByUser findAllByUser;
    @MockBean
    private UpdateHabit updateHabit;
    @MockBean
    private ChangeDoHabit changeDoHabit;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FactoryHabit factoryHabit;
    @Mock
    private Client client;
    @Mock
    private Habit habit;

//    @Test
//    @DisplayName("Should be return status 201, created ")
//    void scneario01() throws Exception {
//        Client client1 = new Client(
//             1L,
//             null,
//             null,
//             null,
//             null,
//                null,
//                null,
//                null,
//                null
//        );
//
//        var json = """
//                {
//                  "description": "Descrição aleatória de algo muito importante",
//                  "start":  "10/10/2024",
//                  "clientId": 1
//                }
//                """;
//        when(findClient.findClient(1L)).thenReturn(client1);
//        var response = mockMvc.perform(
//                post("/habit")
//                        .content(json)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andReturn().getResponse();
//
//        //Assert
//        assertEquals(201, response.getStatus());
//    }
//
//    @Test
//    @DisplayName("Should be return status 200, update ")
//    void scneario02() throws Exception {
//        var json = """
//                {
//                   "date": "10/10/2024"
//                }
//                """;
//        var response = mockMvc.perform(
//                patch("/habit/change-habit-status/10")
//                        .content(json)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andReturn().getResponse();
//
//        //Assert
//        assertEquals(200, response.getStatus());
//    }
//
//    @Test
//    @DisplayName("Should return status 204, delete ")
//    void scenario03() throws Exception {
//        var response = mockMvc.perform(
//                delete("/habit/1")
//        ).andReturn().getResponse();
//        // Assertions
//        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
//        assertTrue(response.getContentAsString().isEmpty(), "Response body should be empty for status 204");
//    }
//
//    @Test
//    @DisplayName("Should return status 200 to findByID")
//    void scenario04() throws Exception {
//
//        List<Progress> progresses = new ArrayList<>();
//        Habit habit1 = null;
//        String id = "1";
//        when(findHabit.findById(1L)).thenReturn(habit1);
//
//        MockHttpServletResponse response = mockMvc.perform(
//                get("/habit/{id}", id)
//        ).andReturn().getResponse();
//        // Assert
//        assertEquals(200, response.getStatus());
//    }
//
//    @Test
//    @DisplayName("Should return status 200 to find all by user")
//    void scenario05() throws Exception {
//
//        Client client1 = new Client(
//                1L,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null
//        );
//
//        List<Habit> habits = new ArrayList<>();
//
//        when(findClient.findClient(1L)).thenReturn(client1);
//
//        when(findAllByUser.findAllByUser(1L)).thenReturn(habits);
//
//        String id = "1";
//        MockHttpServletResponse response = mockMvc.perform(
//                get("/habit/find-all/{id}", id)
//        ).andReturn().getResponse();
//
//        // Verifica se o status retornado é 200 (OK)
//        assertEquals(200, response.getStatus());
//    }
//
//    @Test
//    @DisplayName("Should return status 204 when to delete the habit")
//    void scenario06() throws Exception {
//        List<Progress> progresses = new ArrayList<>();
//        Habit habit1 = new Habit(
//                1L,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null,
//                progresses
//
//        );
//        String id = "1";
//
//        MockHttpServletResponse response = mockMvc.perform(
//                delete("/habit/{id}", id)
//        ).andReturn().getResponse();
//        // Assert
//        assertEquals(204, response.getStatus());
//    }

}
