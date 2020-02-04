package com.mytwitter.controller;

import com.mytwitter.entity.Tweet;
import com.mytwitter.entity.User;
import com.mytwitter.repository.TweetRepository;
import com.mytwitter.service.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/application")
public class ApplicationController {

    private final TweetRepository tweetRepository;

    public ApplicationController(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @ModelAttribute("user")
    public User getUser(@AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();

        return user;
    }

    @ModelAttribute("tweet")
    public Tweet getTweet(@AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();
        Tweet tweet = new Tweet();
        tweet.setUser(user);
        return tweet;
    }
    @GetMapping("")
    public String home(){

        return "application/home";
    }

    @GetMapping("/tweets")
    public String getTweets(Model model){
        List<Tweet> tweets = tweetRepository.findAll();
        Collections.sort(tweets);
        model.addAttribute("tweets", tweets);
        return "application/tweets";
    }

    @GetMapping("/my-tweets")
    public String getMyTweets(Model model, @AuthenticationPrincipal CurrentUser customUser){
        List<Tweet> myTweets = tweetRepository.findAllByUserId(customUser.getUser().getId());
        Collections.sort(myTweets);
        model.addAttribute("myTweets", myTweets);
        return "application/my-tweets";
    }


}
