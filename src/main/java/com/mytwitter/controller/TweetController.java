package com.mytwitter.controller;

import com.mytwitter.entity.Tweet;
import com.mytwitter.repository.TweetRepository;
import com.mytwitter.service.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/tweet")
public class TweetController {

    private final TweetRepository tweetRepository;

    public TweetController(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @PostMapping("/add")
    public String saveTweet(@AuthenticationPrincipal CurrentUser customUser, @ModelAttribute String jspAddress, @Valid Tweet tweet, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "/application/tweets";
//        }
        tweet.setUser(customUser.getUser());
        tweetRepository.save(tweet);
        return "redirect:/application/tweets";
    }
}
