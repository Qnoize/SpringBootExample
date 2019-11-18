package ru.ejmentor.SpringBootExample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ejmentor.SpringBootExample.model.Role;
import ru.ejmentor.SpringBootExample.model.User;
import ru.ejmentor.SpringBootExample.repository.RoleRepository;
import ru.ejmentor.SpringBootExample.repository.UserRepository;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
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
            userRepository.save(user);
        }
    }

    public boolean userExistByName(String userName) { return true; }

    @Override
    public User getByName(String userName) {
        User user = userRepository.findByUserName(userName);
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return user;
    }
}
