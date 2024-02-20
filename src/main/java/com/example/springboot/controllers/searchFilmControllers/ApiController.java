package com.example.springboot.controllers.searchFilmControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApiController {

    @GetMapping("/")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/register")
    public ModelAndView register(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        ModelAndView modelAndView = new ModelAndView("registration-success");
        modelAndView.addObject("username", username);
        modelAndView.addObject("email", email);
        modelAndView.addObject("password", password);
        return modelAndView;
    }

}
