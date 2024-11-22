package br.com.monitodehabitos.monitodehabitos.infra.controller.habitDto.response;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record WeekDto(
        String id,
        LocalDate startDate,
        LocalDate endDate,
        double percentagePerDay,
        List<ProgresDto> progresses,
        double totalePercentage
) {

    public WeekDto(Week week) {
        this(
        week.getId(),
        week.getStartDate(),
        week.getEndDate(),
        week.getPercentagePerDay(),
        week.getProgresses().stream().map(
                p -> new ProgresDto(p.getCurrentDate(), p.getProgressEnumStatus())
        ).collect(Collectors.toList()),
        week.getTotalPercentage()
        );
    }
}
