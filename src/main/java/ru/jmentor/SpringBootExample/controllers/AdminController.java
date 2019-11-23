package ru.jmentor.SpringBootExample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.jmentor.SpringBootExample.model.User;
import ru.jmentor.SpringBootExample.service.UserService;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private UserService service;

    @Autowired
    public void setService(UserService service) { this.service = service; }

    @GetMapping(value = "/admin")
    public ModelAndView viewAdminPage(
            ModelAndView modelAndView,
            @ModelAttribute("user") User user) {
        List<User> users = service.getAllUsers();
        modelAndView.setViewName("adminMainPage");
        modelAndView.addObject("list", users);
        return modelAndView;
    }

    @PostMapping(value = "/admin")
    public ModelAndView adminCreateUser(
            @ModelAttribute("user") User user,
            ModelAndView modelAndView) {

        if (!user.getUserName().isEmpty()&& !user.getUserPassword().isEmpty()) { service.saveUser(user); }
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @PostMapping(value = "/admin/delete")
    public ModelAndView adminDeleteUser(
            @RequestParam("id") Long id,
            ModelAndView modelAndView) {

        if (id != null) { service.deleteUserById(id); }
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @PostMapping(value = "/admin/edit")
    public ModelAndView adminEditUser(
            @ModelAttribute("user") User user,
            @RequestParam("userPassword") String newUserPassword,
            ModelAndView modelAndView) {

            user.setUserPassword(newUserPassword);
            service.editUser(user);
            modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }
}
