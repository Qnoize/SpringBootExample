package ru.ejmentor.SpringBootExample.controllers;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.servlet.ModelAndView;@Controllerpublic class ErrorController {    @GetMapping(value = "/error")    public ModelAndView viewLoginPage(ModelAndView modelAndView) {            modelAndView.setViewName("errorPage");        return modelAndView;    }}