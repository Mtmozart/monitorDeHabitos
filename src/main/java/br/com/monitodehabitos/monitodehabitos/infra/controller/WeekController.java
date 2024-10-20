package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Client.FindClient;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.FindHabit;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.CreateWeek;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.FindWeekById;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.RemoveWeek;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryWeek;
import br.com.monitodehabitos.monitodehabitos.infra.controller.weekDto.request.CreateWeekDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/week")
public class WeekController {

    private final FindWeekById findWeekById;
    private final CreateWeek createWeek;
    private final FactoryWeek factoryWeek;
    private final RemoveWeek removeWeek;
    private final FindHabit findHabit;
    private final FindClient findClient;

    public WeekController(FindWeekById findWeekById, CreateWeek createWeek, FactoryWeek factoryWeek, RemoveWeek removeWeek, FindHabit findHabit, FindClient findClient) {
        this.findWeekById = findWeekById;
        this.createWeek = createWeek;
        this.factoryWeek = factoryWeek;
        this.removeWeek = removeWeek;
        this.findHabit = findHabit;
        this.findClient = findClient;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateWeekDto createWeekDto) throws HabitExeption {
        Client client = this.findClient.findClient(createWeekDto.clientId());
        Habit habit = this.findHabit.findById(createWeekDto.habitId());
        Week week = this.factoryWeek.createWeekWithIdHabitClient(null, habit, client);
        this.createWeek.create(week);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Week> findById(@PathVariable("id") Long id) throws WeekException {
        Week week = this.findWeekById.findById(id);
        return ResponseEntity.ok().body(week);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            // Tenta remover a semana pelo ID
            this.removeWeek.remove(id);
            // Retorna uma resposta 204 No Content
            return ResponseEntity.noContent().build();
        } catch (WeekException e) {
            // Trata o caso em que a semana não é encontrada
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Semana não encontrada.");
        } catch (Exception e) {
            // Trata outras exceções potenciais
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro.");
        }
    }

}
