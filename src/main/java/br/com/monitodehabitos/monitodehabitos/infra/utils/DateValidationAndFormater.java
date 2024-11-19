package br.com.monitodehabitos.monitodehabitos.infra.utils;


import br.com.monitodehabitos.monitodehabitos.domain.enums.HabitsErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateValidationAndFormater {

    public LocalDate validate(String date) throws HabitExeption {
        if(date == null || date.isEmpty() || date.isBlank()){
            throw new HabitExeption(HabitsErrorEnum.HBT0009.getMessage());
        }
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       return LocalDate.parse(date, formatter);
    }
}
