package com.mytwitter.controller;

import com.mytwitter.entity.User;
import com.mytwitter.repository.UserRepository;
import com.mytwitter.service.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute("user")
    public User getUser(@AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();

        return user;
    }

    @GetMapping("/edit")
    public String editUser(){
        return "user/edit";
    }
    @PostMapping("/edit")
    public String saveEditedEmail(@Valid User user, @AuthenticationPrincipal CurrentUser customUser){
        user.setPassword(customUser.getUser().getPassword());
        userRepository.save(user);
        return "redirect:/user/edit";
    }
}
