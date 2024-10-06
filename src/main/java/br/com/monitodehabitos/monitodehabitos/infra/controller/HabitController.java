package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.FindClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.*;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.AddPercentage;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.CreateWeek;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.RemovePercentage;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.domain.enums.HabitsErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryWeek;
import br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request.CreateHabitDto;
import br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request.UpdateHabitDto;
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
    private final UpdateHabit updateHabit;
    private final ChangeDoHabit changeDoHabit;
    private final FactoryWeek factoryWeek;
    private final CreateWeek createWeek;
    private final AddPercentage addPercentage;
    private final RemovePercentage removePercentage;

    public HabitController(FactoryHabit factoryHabit, CreateHabit createHabit, FindClient findClient, FindHabit findHabit, DeleteHabit deleteHabit, FindAllByUser findAllByUser, UpdateHabit updateHabit, ChangeDoHabit changeDoHabit, FactoryWeek factoryWeek, CreateWeek createWeek, AddPercentage addPercentage, RemovePercentage removePercentage) {
        this.factoryHabit = factoryHabit;
        this.createHabit = createHabit;
        this.findClient = findClient;
        this.findHabit = findHabit;
        this.deleteHabit = deleteHabit;
        this.findAllByUser = findAllByUser;
        this.updateHabit = updateHabit;
        this.changeDoHabit = changeDoHabit;
        this.factoryWeek = factoryWeek;
        this.createWeek = createWeek;
        this.addPercentage = addPercentage;
        this.removePercentage = removePercentage;
    }

    @PostMapping
    public ResponseEntity<ResponseHabitDto> create(@RequestBody CreateHabitDto data) throws HabitExeption, WeekException {
        Client client = this.findClient.findClient(data.clientId());
        if (data.start() != null && !data.start().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateStart = LocalDate.parse(data.start(), formatter);
            Habit habit = this.factoryHabit.withDescriptionAndDate(null, client, data.description(), dateStart);
            client.addHabit(habit);
            Habit newHabit = this.createHabit.create(habit);
            Week week = this.factoryWeek.createWeekWithIdHabitClient(null, newHabit, client);
            client.addWeek(week);
            this.createWeek.create(week);
            ResponseHabitDto responseDto = new ResponseHabitDto(habit);
            URI location = URI.create("/api/habits/" + habit.getId());
            return ResponseEntity.created(location).body(responseDto);
        } else {
            throw new HabitExeption(HabitsErrorEnum.HBT0009.getMessage());
        }
    }

    @PatchMapping("change-habit-status/{id}")
    public ResponseEntity<Habit> changeDo(@PathVariable Long id) throws HabitExeption, WeekException {
        Habit habit = this.changeDoHabit.changeDoHabit(id);
        if(habit.getDone()){
            this.addPercentage.addPercentage(habit.getPercentageForDay(), habit.getId());
        } else if (!habit.getDone()) {
            System.out.println("entrei aqui");
            //this.removePercentage.removePercentage(habit.getPercentageForDay(), habit.getId());
        }
        return ResponseEntity.ok(habit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseHabitDto> update(@PathVariable Long id, @RequestBody UpdateHabitDto data) throws HabitExeption {
        LocalDate dateStart = null;
        if (data.start() != null && !data.start().isBlank() && !data.start().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dateStart = LocalDate.parse(data.start(), formatter);
        }
        Habit habit = this.factoryHabit.update(data.description(), dateStart);
        Habit update = this.updateHabit.update(id, habit);
        ResponseHabitDto responseDto = new ResponseHabitDto(update);
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
                .map(ResponseHabitDto::new)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }
}
