package br.com.monitodehabitos.monitodehabitos.infra.controller;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.FindWeekById;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weeks")
public class WeekController {

    private final FindWeekById findWeekById;

    public WeekController(FindWeekById findWeekById) {
        this.findWeekById = findWeekById;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Week> findById(@PathVariable("id") Long id) throws WeekException {
        Week week = this.findWeekById.findById(id);
        return ResponseEntity.ok().body(week);
    }
}
