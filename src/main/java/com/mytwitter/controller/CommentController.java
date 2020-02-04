package com.mytwitter.controller;

import com.mytwitter.entity.Comment;
import com.mytwitter.repository.CommentRepository;
import com.mytwitter.repository.TweetRepository;
import com.mytwitter.service.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentRepository commentRepository;
    private final TweetRepository tweetRepository;

    public CommentController(CommentRepository commentRepository, TweetRepository tweetRepository) {
        this.commentRepository = commentRepository;
        this.tweetRepository = tweetRepository;
    }

    @PostMapping("/add/{id}")
    public String saveComment(@PathVariable long id, @Valid Comment comment, BindingResult bindingResult, @AuthenticationPrincipal CurrentUser customUser){
//        if (bindingResult.hasErrors()) {
//            System.out.println(bindingResult.getAllErrors().toString());
//            return "/application/tweets";
//        }
        //Skąd ma id?
        comment.setTweet(tweetRepository.getById(id));
        comment.setUser(customUser.getUser());
        comment.setId(0);
        commentRepository.save(comment);
        return "redirect:/application/tweets";
    }

}
