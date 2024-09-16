package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.FindClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.CreateHabit;
import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryClient;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public Habit create(@RequestBody CreateHabitController data) throws HabitExeption {
        Client client = this.findClient.findClient(data.clientId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateStart = LocalDate.parse(data.start(), formatter);
        Habit habit = this.factoryHabit.withDescriptionAndDate(null, client, data.description(),dateStart);
        client.addHabit(habit);
        return this.createHabit.create(habit);
    }
}
