package br.com.monitodehabitos.monitodehabitos.config;

import br.com.monitodehabitos.monitodehabitos.application.gateway.HabitRepository;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.CreateHabit;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.UpdateHabit;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.DeleteHabit;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.FindHabit;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Habit.FindAllByUser;
import br.com.monitodehabitos.monitodehabitos.infra.gateways.HabitRepositoryJPA;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HabitConfig {

    @Bean
    public CreateHabit createHabit(HabitRepository habitRepository) {
        return new CreateHabit(habitRepository);
    }

    @Bean
    public UpdateHabit updateHabit(HabitRepository habitRepository) {
        return new UpdateHabit(habitRepository);
    }

    @Bean
    public DeleteHabit deleteHabit(HabitRepository habitRepository) {
        return new DeleteHabit(habitRepository);
    }

    @Bean
    public FindHabit findHabit(HabitRepository habitRepository) {
        return new FindHabit(habitRepository);
    }

    @Bean
    public FindAllByUser findAllByUser(HabitRepository habitRepository) {
        return new FindAllByUser(habitRepository);
    }
    @Bean
    HabitRepositoryJPA habitRepositoryJPA(HabitEntityRepository habitEntityRepository){
        return new HabitRepositoryJPA(habitEntityRepository);

    }
}
