package br.com.monitodehabitos.monitodehabitos.infra.utils;

import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.AddPercentage;
import br.com.monitodehabitos.monitodehabitos.application.useCases.Week.RemovePercentage;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.HabitEntity;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("Observer")
public class HabitChangeListener {

    private final AddPercentage addPercentage;
    private final RemovePercentage removePercentage;

    public HabitChangeListener(AddPercentage addPercentage, RemovePercentage removePercentage) {
        this.addPercentage = addPercentage;
        this.removePercentage = removePercentage;
    }

    @EventListener
    public void handleHabitChangedEvent(HabitChangeEvent event) throws WeekException {
        HabitEntity habitEntity = event.getHabitEntity();
        boolean change = event.isChange();

        // Aqui você pode adicionar a lógica que deseja executar ao receber a notificação
        System.out.println("Hábito alterado: " + habitEntity.getId() + ", Mudança: " + change);

        // Por exemplo, você pode chamar o repositório de semanas para atualizar o progresso
        // weekRepository.updateProgress(habitEntity, change); // Exemplo fictício
            if (!change) {
                System.out.println("Sou chamado: entrei em porcetagem para remover");
                this.removePercentage.removePercentage(habitEntity.getPercentageForDay(), habitEntity.getId());
            } else {
                System.out.println("Sou chamado: entrei em porcetagem para addicionar");
                this.addPercentage.addPercentage(habitEntity.getPercentageForDay(), habitEntity.getId());
            }
        }


}
