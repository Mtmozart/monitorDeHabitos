package br.com.monitodehabitos.monitodehabitos.config;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.application.gateway.WeekRepository;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.*;
import br.com.monitodehabitos.monitodehabitos.domain.factories.FactoryHabit;
import br.com.monitodehabitos.monitodehabitos.infra.gateways.HabitEntityMapper;
import br.com.monitodehabitos.monitodehabitos.infra.gateways.HabitRepositoryJPA;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntityRespository;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.ProgressEntityRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HabitConfig {

    @Bean
    FactoryHabit factoryHabit() {
        return new FactoryHabit();
    }

    @Bean
    CreateHabit createHabit(HabitRepository habitRepository) {
        return new CreateHabit(habitRepository);
    }

    @Bean
    FindHabit findHabit(HabitRepository habitRepository) {
        return new FindHabit(habitRepository);
    }

    @Bean
    DeleteHabit deleteHabit(HabitRepository habitRepository) {
        return new DeleteHabit(habitRepository);
    }

    @Bean
    UpdateHabit updateHabit(HabitRepository habitRepository) {
        return new UpdateHabit(habitRepository);
    }

    @Bean
    FindAllByUser findAllByUser(HabitRepository habitRepository) {
        return new FindAllByUser(habitRepository);
    }

    @Bean
    ChangeDoHabit changeDoHabit(HabitRepository habitRepository) {
        return new ChangeDoHabit(habitRepository);
    }

    @Bean
    HabitRepositoryJPA habitRepositoryJPA(HabitEntityRespository habitEntityRespository, HabitEntityMapper habitEntityMapper) {
        return new HabitRepositoryJPA(habitEntityRespository, habitEntityMapper);
    }

    @Bean
    HabitEntityMapper habitEntityMapper() {
        return new HabitEntityMapper();
    }
    }
