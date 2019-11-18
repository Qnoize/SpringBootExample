package ru.ejmentor.SpringBootExample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.ejmentor.SpringBootExample.model.User;
import ru.ejmentor.SpringBootExample.service.UserService;

@Controller
public class RegisterController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/register")
    public ModelAndView viewRegisterPage(ModelAndView modelAndView) {
        modelAndView.setViewName("registerUser");
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public ModelAndView registerNewUser(
            @ModelAttribute("user") User user,
            ModelAndView modelAndView) {

        if (user.getUserName().isEmpty() | user.getUserPassword().isEmpty()) {
            modelAndView.addObject("error", "Error - Empty user Login or Password");
            modelAndView.setViewName("registerUser");
        } else {
            service.saveUser(user);
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }
}
