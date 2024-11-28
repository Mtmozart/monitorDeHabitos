package br.com.monitodehabitos.monitodehabitos.config;

import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.*;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryWeek;
import br.com.monitodehabitos.monitodehabitos.infra.gateways.WeekEntityMapper;
import br.com.monitodehabitos.monitodehabitos.infra.gateways.WeekRepositoryJPA;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ProgressEntityRepository;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.WeekEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeekConfig {
    @Bean
    FactoryWeek factoryWeek(){
        return new FactoryWeek();
    }
    @Bean
    CreateWeek createWeek(WeekRepository weekRepository){
        return new CreateWeek(weekRepository);
    }
    @Bean
    FindWeekById findWeekById(WeekRepository weekRepository){
        return new FindWeekById(weekRepository);
    }
    @Bean
    RemoveWeek removeWeek(WeekRepository weekRepository){
        return new RemoveWeek(weekRepository);
    }
    @Bean
    AddPercentage addPercentage(WeekRepository weekRepository){
        return new AddPercentage(weekRepository);
    }
    @Bean
    RemovePercentage removePercentage(WeekRepository weekRepository){
        return new RemovePercentage(weekRepository);
    }
    @Bean
    ReturnPercentage returnPercentage(WeekRepository weekRepository){
        return new ReturnPercentage(weekRepository);
    }
    @Bean
    ChangeProgress changeProgress(WeekRepository weekRepository){
        return new ChangeProgress(weekRepository);
    }

    @Bean
    WeekRepositoryJPA weekRepositoryJPA(WeekEntityRepository weekEntityRepository, WeekEntityMapper weekEntityMapper, ProgressEntityRepository progressEntityRepository){
        return new WeekRepositoryJPA(weekEntityRepository, weekEntityMapper, progressEntityRepository);
    }
    @Bean
    WeekEntityMapper weekEntityMapper(){
        return new WeekEntityMapper();
    }

}
