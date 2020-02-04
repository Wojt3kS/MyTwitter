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
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setPassword("admin");
        user.setEmail("admin@admin.com");
        userService.saveUser(user);
        return "admin";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "Hello " + entityUser.toString();
    }


    @GetMapping("/registration")
    public String registrationForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "/login/registration";
    }

    @PostMapping("/registration")
    public String register(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "/login/registration";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
}
