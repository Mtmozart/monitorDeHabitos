package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;
import br.com.monitodehabitos.monitodehabitos.domain.enums.UserErroEnum;
import br.com.monitodehabitos.monitodehabitos.domain.enums.WeekErrorEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserException;
import br.com.monitodehabitos.monitodehabitos.domain.exception.WeekException;

import java.time.LocalDateTime;
import java.util.*;

public class Client extends User {
    private Boolean isClient;
    private List<Habit> habits = new ArrayList<>();
    public Client() {
        super();
    }
    //contructor para create
    public Client(String email, String password, String name, LocalDateTime createdAt, LocalDateTime updatedAt,
                  Address address, TypeUserEnum typeUserEnum, Boolean isClient) {
        super(UUID.randomUUID().toString(), email, password, name, createdAt, updatedAt, address, typeUserEnum);
        this.isClient = isClient;
    }

    public Client(String id, String email, String password, String name, LocalDateTime createdAt, LocalDateTime updatedAt, Address address, TypeUserEnum typeUserEnum, Boolean isClient, List<Habit> habits) {
        super(id, email, password, name, createdAt, updatedAt, address, typeUserEnum);
        this.isClient = isClient;
        this.habits = habits;

    }

    public Boolean getClient() {
        return isClient;
    }

    public void updateClient(Client newClient) throws UserException {
        if (newClient == null) {
            throw new UserException(UserErroEnum.USR0002.getMessage());
        }
        if (newClient.getEmail() != null && !newClient.getEmail().isEmpty()) {
            setEmail(newClient.getEmail());
        }
        if (newClient.getPassword() != null && !newClient.getPassword().isEmpty()) {
            setPassword(newClient.getPassword());
        }
        if (newClient.getName() != null && !newClient.getName().trim().isEmpty()) {
            setName(newClient.getName());
        }
        if (newClient.getAddress() != null) {
            updateAddress(newClient.getAddress());
        }
        setUpdatedAt(LocalDateTime.now());
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void addHabit(Habit habit) throws HabitExeption {
        if (habit == null) {
            throw new HabitExeption("O hábito não pode ser nulo");
        }
        this.habits.add(habit);
        habit.setClient(this);
    }

    public void removeHabit(Habit habit) throws HabitExeption {
        if (habit == null) {
            throw new HabitExeption("O hábito não pode ser nulo");
        }
        this.habits.remove(habit);
        habit.setClient(null);
    }

}
