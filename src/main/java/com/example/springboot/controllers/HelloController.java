package com.example.springboot.controllers;

import com.example.springboot.models.PersonModel;
import com.example.springboot.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Objects;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index(@RequestParam(name = "name", required = false) String name, Model model) {
        PersonModel person = null;
        try {
            person = (PersonModel) Utils.readJson("person.json", PersonModel.class);
        }
        catch (IOException _) {
        }
        if (name == null && person != null) {
            model.addAttribute("name", person.getName());
        }
        else {
            model.addAttribute("name", "");
        }

        model.addAttribute("person", person);
        return "greeting";
    }

    @GetMapping("/person")
    public String showForm(Model model) {
        PersonModel person = null;
        try {
            person = (PersonModel) Utils.readJson("person.json", PersonModel.class);
        }
        catch (IOException _) {
        }
        model.addAttribute("personForm", Objects.requireNonNullElseGet(person, PersonModel::new));
        model.addAttribute("person", person);
        return "personForm";
    }

    @GetMapping("/person-details")
    @PostMapping("/person-details")
    public String personDetails(Model model) {
        PersonModel person;
        try {
            person = (PersonModel) Utils.readJson("person.json", PersonModel.class);
        }
        catch (IOException e) {
            person = null;

        }
        if (person == null)
            return "redirect:person";
        model.addAttribute("person", person);
        return "personDetails";
    }

    @PostMapping("/person")
    public String processForm(@ModelAttribute PersonModel personForm) throws IOException {
        Utils.writeJson("person.json", personForm);
        return "redirect:person-details";
    }

    @GetMapping("/delete")
    public String processForm() throws IOException {
        Utils.delete("person.json");
        return "redirect:person-details";
    }
}
