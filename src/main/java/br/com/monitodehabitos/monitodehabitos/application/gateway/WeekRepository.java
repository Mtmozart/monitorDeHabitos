package br.com.monitodehabitos.monitodehabitos.application.gateway;

import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

public interface WeekRepository {

    Week save(Week week);

   Week findById(Long id) throws WeekException;

    void delete(Long id) throws WeekException;


    Boolean addPercentage(double add);

    Boolean removePercentage(double add);

    double getPercentage();
}
