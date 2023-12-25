package com.ecograd.ecograd.web.controller;

import com.ecograd.ecograd.model.Role;
import com.ecograd.ecograd.model.exceptions.InvalidArgumentsException;
import com.ecograd.ecograd.model.exceptions.PasswordsDoNotMatchException;
import com.ecograd.ecograd.model.exceptions.UsernameAlreadyExistsException;
import com.ecograd.ecograd.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        return "register";
    }

    @PostMapping
    public String register(@RequestParam (required = false)String username,
                           @RequestParam (required = false)String password,
                           @RequestParam (required = false)String name,
                           @RequestParam (required = false)String surname,
                           @RequestParam (required = false)String email,
                           @RequestParam (required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth
    ) {
        try{
            this.userService.register(username, password, name, surname, email, dateOfBirth, 0, Role.USER);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException | UsernameAlreadyExistsException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
