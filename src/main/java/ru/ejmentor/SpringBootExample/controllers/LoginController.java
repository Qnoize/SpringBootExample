package ru.ejmentor.SpringBootExample.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping(value = "/")
    public ModelAndView viewLoginPage(
            Authentication authentication,
            ModelAndView modelAndView,
            HttpServletRequest request) {

        if(authentication != null){ modelAndView.setViewName("userHome"); }

        if(request.getParameterMap().containsKey("error")){
            modelAndView.addObject("error", "Error - Wrong user Login or Password");
        }
        modelAndView.setViewName("loginUser");
        return modelAndView;
    }
}
