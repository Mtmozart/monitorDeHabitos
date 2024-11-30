package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.FindClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.*;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request.ChangeDoneDto;
import br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request.CreateHabitDto;
import br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.request.UpdateHabitDto;
import br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.response.ResponseHabitDto;
import br.com.monitodehabitos.monitodehabitos.infra.utils.DateValidationAndFormater;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    private final DateValidationAndFormater dateValidationAndFormater;


    public HabitController(FactoryHabit factoryHabit, CreateHabit createHabit, FindClient findClient, FindHabit findHabit, DeleteHabit deleteHabit, FindAllByUser findAllByUser, UpdateHabit updateHabit, ChangeDoHabit changeDoHabit, DateValidationAndFormater dateValidationAndFormater) {
        this.factoryHabit = factoryHabit;
        this.createHabit = createHabit;
        this.findClient = findClient;
        this.findHabit = findHabit;
        this.deleteHabit = deleteHabit;
        this.findAllByUser = findAllByUser;
        this.updateHabit = updateHabit;
        this.changeDoHabit = changeDoHabit;
        this.dateValidationAndFormater = dateValidationAndFormater;
    }

    @PostMapping
    public ResponseEntity<ResponseHabitDto> create(@RequestBody @Valid CreateHabitDto data) throws HabitExeption, WeekException {

        Client client = this.findClient.findClient(data.clientId());
        var dateStart = this.dateValidationAndFormater.validate(data.start());
        var dateEnd = this.dateValidationAndFormater.validate(data.end());
        Habit habit = this.factoryHabit.withDescriptionAndDate(client, data.description(), data.status(), dateStart, dateEnd);
        var newHabit = this.createHabit.create(habit);
        ResponseHabitDto responseDto = new ResponseHabitDto(newHabit);
        URI location = URI.create("/api/habits/" + newHabit.getId());
        return ResponseEntity.created(location).body(responseDto);
    }

    @PatchMapping("change-habit-status/{id}")
    public ResponseEntity<ResponseHabitDto> changeDo(@PathVariable String id, @RequestBody ChangeDoneDto dateProgress) throws HabitExeption, WeekException {
        Habit habit = this.changeDoHabit.changeDoHabit(id, dateProgress.status());
        return ResponseEntity.ok(new ResponseHabitDto(habit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseHabitDto> update(@PathVariable String id, @RequestBody UpdateHabitDto data) throws HabitExeption {

        Habit habit = this.factoryHabit.updateNoDateStater(data.description(), data.done());
        Habit update = this.updateHabit.update(id, habit);
        ResponseHabitDto responseDto = new ResponseHabitDto(update);
        return ResponseEntity.ok(responseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) throws HabitExeption {
        this.deleteHabit.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseHabitDto> findById(@PathVariable String id) throws HabitExeption {
        Habit habit = this.findHabit.findById(id);
        ResponseHabitDto responseHabitDto = new ResponseHabitDto(habit);
        return ResponseEntity.ok(responseHabitDto);
    }

    @GetMapping("find-all/{userId}")
    public ResponseEntity<List<ResponseHabitDto>> findAllByUserId(@PathVariable String userId) throws HabitExeption {
        List<Habit> habits = this.findAllByUser.findAllByUser(userId);
        List<ResponseHabitDto> responseDtos = habits.stream()
                .map(ResponseHabitDto::new)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }
}
