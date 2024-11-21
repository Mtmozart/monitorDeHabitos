package br.com.monitodehabitos.monitodehabitos.application.gateway;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

public interface WeekRepository {

    Week save(Week week);

   Week findById(String id) throws WeekException;

    void delete(String id) throws WeekException;

    Boolean addPercentage(double add, String id) throws WeekException;

    Boolean removePercentage(double add,  String id) throws WeekException;

    double getPercentage();
}
