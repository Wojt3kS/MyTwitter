package com.mytwitter.controller;

import com.mytwitter.entity.Tweet;
import com.mytwitter.repository.CommentRepository;
import com.mytwitter.repository.TweetRepository;
import com.mytwitter.service.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/tweet")
public class TweetController {

    private final TweetRepository tweetRepository;
    private final CommentRepository commentRepository;

    public TweetController(TweetRepository tweetRepository, CommentRepository commentRepository) {
        this.tweetRepository = tweetRepository;
        this.commentRepository = commentRepository;
    }

    @PostMapping("/add")
    public String saveTweet(@AuthenticationPrincipal CurrentUser customUser, @RequestParam String jspAddress,
                            @Valid Tweet tweet, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().toString());
            return jspAddress;
        }
        tweet.setUser(customUser.getUser());
        tweetRepository.save(tweet);
        return "redirect:" + jspAddress;
    }

    @RequestMapping("/{id}")
    public String getTweetDetails(@PathVariable long id, Model model) {
        Tweet tweet = tweetRepository.getById(id);
        model.addAttribute("tweet", tweet);
        return "/tweet/details";
    }

    @RequestMapping("/edit/{id}")
    public String editTweet(@PathVariable long id, Model model, @AuthenticationPrincipal CurrentUser customUser) {
        Tweet tweet = tweetRepository.getById(id);
        if (!customUser.getUser().getUsername().equals(tweet.getUser().getUsername())) {
            return "/tweet/access-not-allowed";
        }
        model.addAttribute("tweet", tweet);
        return "/tweet/edit";
    }

    @PostMapping("/edit")
    public String saveEditedTweet(@AuthenticationPrincipal CurrentUser customUser, @RequestParam String jspAddress,
                                  @Valid Tweet tweet, BindingResult bindingResult, @RequestParam String formattedTime) {
//        if (bindingResult.hasErrors()) {
//            System.out.println(bindingResult.getAllErrors().toString());
//            return "/tweet/edit";
//        }
        tweet.setUser(customUser.getUser());
        tweet.setCreated(LocalDateTime.parse(formattedTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        tweetRepository.save(tweet);
        return "redirect:" + jspAddress;
    }

    @RequestMapping("/delete/{id}")
    public String deleteTweet(@PathVariable long id, @AuthenticationPrincipal CurrentUser customUser) {
        Tweet tweet = tweetRepository.getById(id);
        if (!customUser.getUser().getUsername().equals(tweet.getUser().getUsername())) {
            return "/tweet/access-not-allowed";
        }
        commentRepository.deleteAllByTweet(tweet);
        tweetRepository.delete(tweet);
        return "redirect:/application/tweets";
    }
}
