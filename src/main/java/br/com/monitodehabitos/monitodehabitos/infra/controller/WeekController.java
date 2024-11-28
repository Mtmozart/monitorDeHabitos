package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.ChangeProgress;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.CreateWeek;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.FindWeekById;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.ProgressException;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryWeek;
import br.com.monitodehabitos.monitodehabitos.infra.controller.weekDto.request.ChangeProgressStatusDto;
import br.com.monitodehabitos.monitodehabitos.infra.utils.DateValidationAndFormater;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/week")
public class WeekController {

    private final FindWeekById findWeekById;
    private final CreateWeek createWeek;
    private final FactoryWeek factoryWeek;
    private final ChangeProgress changeProgress;
    private final DateValidationAndFormater dateValidationAndFormater;


    public WeekController(FindWeekById findWeekById, CreateWeek createWeek, FactoryWeek factoryWeek, ChangeProgress changeProgress, DateValidationAndFormater dateValidationAndFormater) {
        this.findWeekById = findWeekById;
        this.createWeek = createWeek;
        this.factoryWeek = factoryWeek;
        this.changeProgress = changeProgress;
        this.dateValidationAndFormater = dateValidationAndFormater;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Week> findById(@PathVariable("id") String id) throws WeekException {
        Week week = this.findWeekById.findById(id);
        return ResponseEntity.ok().body(week);
    }

    @PatchMapping("/{id}/change-progress")
    public ResponseEntity<ResponseDto> changeProgressAndPercentage(@PathVariable("id")String id, @RequestBody ChangeProgressStatusDto date) throws ProgressException, WeekException, HabitExeption {
        LocalDate dateFormatted = dateValidationAndFormater.validate(date.progressDate());
        this.changeProgress.changeProgress(id, dateFormatted);
        return ResponseEntity.ok(new ResponseDto("Progresso alterado com sucesso"));
    }

   }
