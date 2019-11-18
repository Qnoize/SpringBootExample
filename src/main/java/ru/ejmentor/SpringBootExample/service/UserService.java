package ru.ejmentor.SpringBootExample.service;

import ru.ejmentor.SpringBootExample.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
    User getById(Long id);
    void deleteUserById(Long id);
    void editUser(User user);
    User getByName(String userName);
}
