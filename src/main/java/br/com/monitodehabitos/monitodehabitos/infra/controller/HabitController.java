package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.FindClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.CreateHabit;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.DeleteHabit;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.FindAllByUser;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.FindHabit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryClient;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request.CreateHabitDto;
import br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request.FindId;
import br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.response.ResponseHabitDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/habit")
public class HabitController {
    private final FactoryHabit factoryHabit;
    private final CreateHabit createHabit;
    private final FindClient findClient;
    private final FindHabit findHabit;
    private final DeleteHabit deleteHabit;
    private final FindAllByUser findAllByUser;
    public HabitController(FactoryHabit factoryHabit, CreateHabit createHabit, FindClient findClient, FindHabit findHabit, DeleteHabit deleteHabit, FindAllByUser findAllByUser) {
        this.factoryHabit = factoryHabit;

        this.createHabit = createHabit;
        this.findClient = findClient;
        this.findHabit = findHabit;
        this.deleteHabit = deleteHabit;
        this.findAllByUser = findAllByUser;
    }

    @PostMapping
    public ResponseEntity<ResponseHabitDto> create(@RequestBody CreateHabitDto data) throws HabitExeption {
        Client client = this.findClient.findClient(data.clientId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateStart = LocalDate.parse(data.start(), formatter);
        Habit habit = this.factoryHabit.withDescriptionAndDate(null, client, data.description(),dateStart);
        client.addHabit(habit);
        this.createHabit.create(habit);
        ResponseHabitDto responseDto = new ResponseHabitDto(habit);
        URI location = URI.create("/api/habits/" + habit.getId());
        return ResponseEntity.created(location).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseHabitDto> findById(@PathVariable Long id) throws HabitExeption {
        Habit habit = this.findHabit.findById(id);
        ResponseHabitDto responseDto = new ResponseHabitDto(habit);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws HabitExeption {
        this.deleteHabit.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("find-all/{userId}")
    public ResponseEntity<List<ResponseHabitDto>> findAllByUserId(@PathVariable Long userId) throws HabitExeption {
        List<Habit> habits = this.findAllByUser.findAllByUser(userId);
        List<ResponseHabitDto> responseDtos = habits.stream()
                .map(habit -> new ResponseHabitDto(habit))
                .collect(Collectors.toUnmodifiableList());
        return ResponseEntity.ok(responseDtos);
    }
}
