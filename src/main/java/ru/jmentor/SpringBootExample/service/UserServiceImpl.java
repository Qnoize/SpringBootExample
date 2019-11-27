package ru.jmentor.SpringBootExample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.jmentor.SpringBootExample.model.Role;
import ru.jmentor.SpringBootExample.model.User;
import ru.jmentor.SpringBootExample.repository.RoleRepository;
import ru.jmentor.SpringBootExample.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder) { this.passwordEncoder = passwordEncoder; }

    @Autowired
    public void setRepository(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAllUsers(){ return userRepository.findAll(); }

    @Override
    public User getById(Long id){
        User user = userRepository.getById(id);
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return user;
    }

    @Override
    public void deleteUserById(Long id){ userRepository.deleteById(id); }

    @Override
    public void editUser(User user){
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setRole(Collections.singleton(new Role(1L, "ROLE_USER")));
        userRepository.save(user);
    }

    @Override
    public void saveUser(User user){
        if(!userExistByName(user.getUserName())){
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            user.setRole(Collections.singleton(new Role(1L, "ROLE_USER")));
            userRepository.saveAndFlush(user);
        }
    }

    public boolean userExistByName(String userName) { return userRepository.isExistByName(userName) != null; }

    @Override
    public User getByName(String userName) {
        User user = userRepository.findByUserName(userName);
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return user;
    }
}
