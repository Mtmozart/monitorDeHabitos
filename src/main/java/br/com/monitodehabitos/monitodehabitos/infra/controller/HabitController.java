package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.FindClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.*;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.enums.HabitsErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request.ChangeDoneDto;
import br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request.CreateHabitDto;
import br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request.UpdateHabitDto;
import br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.response.ResponseHabitDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/habit")
public class HabitController {
    private final FactoryHabit factoryHabit;
    private final CreateHabit createHabit;
    private final FindClient findClient;
    private final FindHabit findHabit;
    private final DeleteHabit deleteHabit;
    private final FindAllByUser findAllByUser;
    private final UpdateHabit updateHabit;
    private final ChangeDoHabit changeDoHabit;


    public HabitController(FactoryHabit factoryHabit, CreateHabit createHabit, FindClient findClient, FindHabit findHabit, DeleteHabit deleteHabit, FindAllByUser findAllByUser, UpdateHabit updateHabit, ChangeDoHabit changeDoHabit) {
        this.factoryHabit = factoryHabit;
        this.createHabit = createHabit;
        this.findClient = findClient;
        this.findHabit = findHabit;
        this.deleteHabit = deleteHabit;
        this.findAllByUser = findAllByUser;
        this.updateHabit = updateHabit;
        this.changeDoHabit = changeDoHabit;

    }

    @PostMapping
    public ResponseEntity<ResponseHabitDto> create(@RequestBody @Valid CreateHabitDto data) throws HabitExeption, WeekException {

        Client client = this.findClient.findClient(data.clientId());

        if (data.start() != null && !data.start().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateStart = LocalDate.parse(data.start(), formatter);
            Habit habit = this.factoryHabit.withDescriptionAndDate(null, client, data.description(), dateStart, LocalDate.of(2024, 11, 5));
            client.addHabit(habit);
            this.createHabit.create(habit);
            ResponseHabitDto responseDto = new ResponseHabitDto(habit);
            URI location = URI.create("/api/habits/" + habit.getId());
            return ResponseEntity.created(location).body(responseDto);
        } else {
            throw new HabitExeption(HabitsErrorEnum.HBT0009.getMessage());
        }
    }

    @PatchMapping("change-habit-status/{id}")
    public ResponseEntity<Habit> changeDo(@PathVariable Long id, @RequestBody ChangeDoneDto dateProgress) throws HabitExeption, WeekException {
        LocalDate dateProgressDate = null;
        if (dateProgress.date() == null || dateProgress.date().isEmpty()) {
            throw new HabitExeption(HabitsErrorEnum.HBT0016.getMessage());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateProgressDate = LocalDate.parse(dateProgress.date(), formatter);
        Habit habit = this.changeDoHabit.changeDoHabit(id, dateProgressDate);
        return ResponseEntity.ok(habit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseHabitDto> update(@PathVariable Long id, @RequestBody UpdateHabitDto data) throws HabitExeption {
        LocalDate dateStart = null;
        if (data.start() != null && !data.start().isBlank() && !data.start().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dateStart = LocalDate.parse(data.start(), formatter);
            Habit habit = this.factoryHabit.update(data.description(), dateStart, LocalDate.of(2024, 11, 1));
            Habit update = this.updateHabit.update(id, habit);
            ResponseHabitDto responseDto = new ResponseHabitDto(update);
            return ResponseEntity.ok(responseDto);
        }
        if (data.start() == null) {
            Habit habit = this.factoryHabit.updateNoDateStater(data.description());
            Habit update = this.updateHabit.update(id, habit);
            ResponseHabitDto responseDto = new ResponseHabitDto(update);
            return ResponseEntity.ok(responseDto);
        }
        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws HabitExeption {
        this.deleteHabit.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseHabitDto> findById(@PathVariable Long id) throws HabitExeption {
        Habit habit = this.findHabit.findById(id);
        ResponseHabitDto responseHabitDto = new ResponseHabitDto(habit);
        return ResponseEntity.ok(responseHabitDto);
    }

    @GetMapping("find-all/{userId}")
    public ResponseEntity<List<ResponseHabitDto>> findAllByUserId(@PathVariable Long userId) throws HabitExeption {
        List<Habit> habits = this.findAllByUser.findAllByUser(userId);
        List<ResponseHabitDto> responseDtos = habits.stream()
                .map(ResponseHabitDto::new)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }
}
