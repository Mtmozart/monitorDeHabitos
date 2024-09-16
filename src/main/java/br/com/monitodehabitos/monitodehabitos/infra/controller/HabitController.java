package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.FindClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.CreateHabit;
import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryClient;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/habit")
public class HabitController {
    private final FactoryHabit factoryHabit;
    private final FactoryClient factoryClient;
    private final CreateHabit createHabit;
    private final FindClient findClient;

    public HabitController(FactoryHabit factoryHabit, FactoryClient factoryClient, CreateHabit createHabit, FindClient findClient) {
        this.factoryHabit = factoryHabit;
        this.factoryClient = factoryClient;
        this.createHabit = createHabit;
        this.findClient = findClient;
    }

    @PostMapping
    public String create() throws HabitExeption {
        Long id = 1L;
        Client client = this.findClient.findClient(id);
        Habit habit = this.factoryHabit.withDescriptionAndDate(null, client, "description", LocalDate.now());
        System.out.println(habit);
        this.createHabit.create(habit);
        return "Hello world";
    }
}
