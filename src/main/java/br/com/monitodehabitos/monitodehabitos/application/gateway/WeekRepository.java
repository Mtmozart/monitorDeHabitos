package br.com.monitodehabitos.monitodehabitos.application.gateway;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.domain.exception.ProgressException;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

import java.time.LocalDate;

public interface WeekRepository {

    Week save(Week week);

    Week findById(String id) throws WeekException;

    void delete(String id) throws WeekException;

    Boolean addPercentage(double add, String id) throws WeekException;

    Boolean removePercentage(double add, String id) throws WeekException;

    void changeProgress(String weekid, LocalDate date) throws ProgressException, WeekException;

    double getPercentage();
}
