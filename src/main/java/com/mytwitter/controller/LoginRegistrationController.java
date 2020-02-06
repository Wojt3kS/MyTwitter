package com.mytwitter.controller;

import com.mytwitter.entity.User;
import com.mytwitter.service.CurrentUser;
import com.mytwitter.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class LoginRegistrationController {

    private final UserService userService;

    public LoginRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login/registration";
    }

    @PostMapping("/registration")
    public String register(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login/registration";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect:/";
    }
}
