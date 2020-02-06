package com.mytwitter.controller;

import com.mytwitter.entity.Message;
import com.mytwitter.entity.User;
import com.mytwitter.repository.MessageRepository;
import com.mytwitter.service.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/message")
public class MessageController {

    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @ModelAttribute("user")
    public User getUser(@AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();

        return user;
    }

    @PostMapping("/add")
    public String addMessage(@ModelAttribute Message message, @AuthenticationPrincipal CurrentUser customUser){
        message.setFromUser(customUser.getUser());
        message.setReaded(false);
        messageRepository.save(message);
        return "redirect:/application/messages";
    }

    @RequestMapping("/{id}")
    public String showMessage(@PathVariable long id, Model model){
        Message message = messageRepository.getById(id);
        message.setReaded(true);
        messageRepository.save(message);
        model.addAttribute("message", message);
        return "message/details";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMessage(@PathVariable long id){
        messageRepository.delete(messageRepository.getById(id));
        return "redirect:/application/messages";
    }
}
