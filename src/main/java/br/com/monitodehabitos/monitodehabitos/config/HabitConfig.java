package br.com.monitodehabitos.monitodehabitos.config;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.CreateHabit;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import br.com.monitodehabitos.monitodehabitos.infra.gateways.HabitEntityMapper;
import br.com.monitodehabitos.monitodehabitos.infra.gateways.HabitRepositoryJPA;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntityRespository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HabitConfig {

    @Bean
    FactoryHabit factoryHabit(){
        return new FactoryHabit();
    }
    @Bean
    CreateHabit createHabit(HabitRepository habitRepository){
        return new CreateHabit(habitRepository);
    }
    @Bean
    HabitRepositoryJPA habitRepositoryJPA(HabitEntityRespository habitEntityRespository, HabitEntityMapper habitEntityMapper){
        return new HabitRepositoryJPA(habitEntityRespository, habitEntityMapper);
    }
    @Bean
    HabitEntityMapper habitEntityMapper(){
        return new HabitEntityMapper();
    }
 }
