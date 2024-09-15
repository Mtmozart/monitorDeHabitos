package br.com.monitodehabitos.monitodehabitos.domain.entities;

import br.com.monitodehabitos.monitodehabitos.domain.Address;
import br.com.monitodehabitos.monitodehabitos.domain.enums.TypeUserEnum;
import br.com.monitodehabitos.monitodehabitos.domain.enums.UserErroEnum;
import br.com.monitodehabitos.monitodehabitos.domain.exception.HabitExeption;
import br.com.monitodehabitos.monitodehabitos.domain.exception.UserException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Client extends User {
  private Boolean isClient;
  private List<Habit> habits = new ArrayList<>();

  public Client() {
    super();
  }

  public Client(String email, String password, String name, LocalDateTime createdAt, LocalDateTime updatedAt,
                Address address, TypeUserEnum typeUserEnum, Boolean isClient) {
    super(email, password, name, createdAt, updatedAt, address, typeUserEnum);
    this.isClient = isClient;
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

  @Override
  public String toString() {
    return "Client{" +
            "email='" + getEmail() + '\'' +
            ", name='" + getName() + '\'' +
            ", createdAt=" + getCreatedAt() +
            ", updatedAt=" + getUpdatedAt() +
            ", address=" + (getAddress() != null ? getAddress().toString() : "null") +
            ", typeUserEnum=" + getTypeUserEnum() +
            ", isClient=" + isClient +
            ", habits=" + habits +
            '}';
  }

}
