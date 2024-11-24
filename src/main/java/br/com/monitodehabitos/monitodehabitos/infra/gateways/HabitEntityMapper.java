package br.com.monitodehabitos.monitodehabitos.infra.gateways;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Client;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Habit;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Progress;
import br.com.monitodehabitos.monitodehabitos.domain.entities.Week;
import br.com.monitodehabitos.monitodehabitos.infra.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

public class HabitEntityMapper {

    public HabitEntity toHabitEntityCreate(Habit habit) {
        return new HabitEntity(
                habit.getId(),
                habit.getDescription(),
                habit.getDone(),
                habit.getStart(),
                habit.getEnd(),
                toClientEntity(habit.getClient()),
                toWeekEntitiesCreate(habit.getWeeks())
        );
    }

    public HabitEntity toHabitEntityWithAllParamentrs(Habit habit) {
        return new HabitEntity(
                habit.getId(),
                habit.getDescription(),
                habit.getDone(),
                habit.getStart(),
                habit.getEnd(),
                toClientEntity(habit.getClient()),
                null
        );
    }

    public Habit toHabitDomainWithAllParameters(HabitEntity habitEntity) {
        return new Habit(
                habitEntity.getId(),
                habitEntity.getDescription(),
                habitEntity.getDone(),
                habitEntity.getStart(),
                habitEntity.getEnd(),
                toClientDomain(habitEntity.getClientEntity()),
                toWeekDomainResponseList(habitEntity.getWeeks())
        );
    }

    public Client toClientDomain(ClientEntity clientEntity) {
        if (clientEntity == null) return null;
        return new Client(
                clientEntity.getId(),
                clientEntity.getEmail(),
                clientEntity.getPassword(),
                clientEntity.getName(),
                clientEntity.getCreatedAt(),
                clientEntity.getUpdatedAt(),
                toAddressDomain(clientEntity.getAddressEntity()),
                clientEntity.getTypeUser(),
                clientEntity.getClient(),
                null
        );
    }

    public Address toAddressDomain(AddressEntity addressEntity) {
        if (addressEntity == null) return null;

        return new Address(
                addressEntity.getCep(),
                addressEntity.getStreet(),
                addressEntity.getCity(),
                addressEntity.getState(),
                addressEntity.getNeighborhood(),
                addressEntity.getNumber(),
                addressEntity.getComplement()
        );
    }

    public ClientEntity toClientEntity(Client client) {
        return new ClientEntity(
                client.getId(),
                client.getEmail(),
                client.getPassword(),
                client.getName(),
                client.getTypeUserEnum(),
                client.getClient(),
                client.getCreatedAt(),
                client.getUpdatedAt(),
                toAddressEntity(client.getAddress()),
                null
        );
    }

    public AddressEntity toAddressEntity(Address address) {
        return new AddressEntity(
                address.getCep(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getNeighborhood(),
                address.getNumber(),
                address.getComplement()
        );
    }

    public ProgressEntity toProgressEntity(Progress progress) {
        return new ProgressEntity(
                progress.getId(),
                progress.getCurrentDate(),
                progress.getProgressEnumStatus(),
                toWeekEntity(progress.getWeek())
        );
    }

    public List<ProgressEntity> toProgressEntityListWithAllParamenters(List<Progress> progresses) {
        return progresses.stream().map(
                this::toProgressEntity
        ).collect(Collectors.toList());
    }

    public ProgressEntity toProgressEntityWithAllParamenters(Progress progress){
        return new ProgressEntity(progress.getId(), progress.getCurrentDate(), progress.getProgressEnumStatus(), toWeekEntity(progress.getWeek()));
    }

    public List<ProgressEntity> toProgressEntityMapper(Habit habit) {
        return null;
    }

    public Progress toProgressDomain(ProgressEntity progressEntity) {
        return new Progress(
                progressEntity.getId(),
                progressEntity.getCurrentDate(),
                progressEntity.getCompleted(),
                null
        );
    }

    public List<Progress> toProgressDomainMapper(List<ProgressEntity> progressEntities) {
        return progressEntities.stream()
                .map(this::toProgressDomain)
                .collect(Collectors.toList());
    }

    public List<WeekEntity> toWeekEntities(List<Week> weeks){
        return weeks.stream().map(
                this::toWeekEntity
        ).collect(Collectors.toList());
    }

    public List<WeekEntity> toWeekEntitiesCreate(List<Week> weeks){
        return weeks.stream().map(
                this::toWeekEntityWithoutProgress
        ).collect(Collectors.toList());
    }

    public WeekEntity toWeekEntity(Week week){
        return new WeekEntity(
                week.getId(),
                week.getStartDate(),
                week.getEndDate(),
                week.getTotalPercentage(),
                this.toHabitEntityWithAllParamentrs(week.getHabit()),
                week.getPercentagePerDay(),
                toProgressEntityListWithAllParamenters(week.getProgresses())

        );


    }
    public WeekEntity toWeekEntityWithoutProgress(Week week){
        return new WeekEntity(
                week.getId(),
                week.getStartDate(),
                week.getEndDate(),
                week.getTotalPercentage(),
                this.toHabitEntityWithAllParamentrs(week.getHabit()),
                week.getPercentagePerDay(),
               toProgressCreate(week.getProgresses())
        );
    }

    public List<ProgressEntity> toProgressCreate(List<Progress> progresses){
        return progresses.stream().map(
                this::toProgressEntityForCreate
        ).collect(Collectors.toList());
    }

    public ProgressEntity toProgressEntityForCreate(Progress progress) {
        return new ProgressEntity(
                progress.getId(),
                progress.getCurrentDate(),
                progress.getProgressEnumStatus(),
                new WeekEntity(
                        progress.getWeek().getId(),
                        progress.getWeek().getStartDate(),
                        progress.getWeek().getEndDate(),
                        progress.getWeek().getTotalPercentage(),
                        null,
                        progress.getWeek().getPercentagePerDay(),
                        null
                )
        );
    }

    public List<Week> toWeekDomainResponseList(List<WeekEntity> weekEntities){
        return weekEntities.stream().map(
                this::toWeekDomainResponse
        ).collect(Collectors.toList());
    }
    public Week toWeekDomainResponse(WeekEntity weekEntity){
        return new Week(
                weekEntity.getId(),
                weekEntity.getStartDate(),
                weekEntity.getEndDate(),
                weekEntity.getPercentagePerDay(),
                toProgressDomainMapper(weekEntity.getProgresses()),
                weekEntity.getTotalPercentage()
        );

    }


    public HabitEntity toHabitEntityWithoutClient(Habit habit) {
        return new HabitEntity(
                habit.getId(),
                habit.getDescription(),
                habit.getDone(),
                habit.getStart(),
                habit.getEnd(),
                toClientEntity(habit.getClient()),
                toWeekEntitiesWihtouHabit(habit.getWeeks())
        );
    }

    public List<WeekEntity> toWeekEntitiesWihtouHabit(List<Week> weeks){
        return weeks.stream().map(
                this::toWeekEntityWithoutHabit
        ).collect(Collectors.toList());
    }

    public WeekEntity toWeekEntityWithoutHabit(Week week){
        return new WeekEntity(
                week.getId(),
                week.getStartDate(),
                week.getEndDate(),
                week.getTotalPercentage(),
                null,
                week.getPercentagePerDay(),
                toProgressEntityListWithoutWeek(week.getProgresses())

        );
    }

    public List<ProgressEntity> toProgressEntityListWithoutWeek(List<Progress> progresses) {
        return progresses.stream().map(
                this::toProgressEntityWithouWeek
        ).collect(Collectors.toList());
    }

    public ProgressEntity toProgressEntityWithouWeek(Progress progress) {
        return new ProgressEntity(
                progress.getId(),
                progress.getCurrentDate(),
                progress.getProgressEnumStatus(),
                null
        );
    }

}
