package com.example.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/searchMovie")
    public String searchMovie() {
        return "searchMovie";
    }


}
