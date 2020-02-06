package com.mytwitter.controller;

import com.mytwitter.entity.Comment;
import com.mytwitter.entity.Tweet;
import com.mytwitter.entity.User;
import com.mytwitter.repository.CommentRepository;
import com.mytwitter.repository.TweetRepository;
import com.mytwitter.service.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentRepository commentRepository;
    private final TweetRepository tweetRepository;

    public CommentController(CommentRepository commentRepository, TweetRepository tweetRepository) {
        this.commentRepository = commentRepository;
        this.tweetRepository = tweetRepository;
    }

    @ModelAttribute("tweet")
    public Tweet getTweet(@AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();
        Tweet tweet = new Tweet();
        tweet.setUser(user);
        return tweet;
    }

    @ModelAttribute("tweets")
    public List<Tweet> getTweets() {
        List<Tweet> tweets = tweetRepository.findAll();
        Collections.sort(tweets);
        tweets.forEach(t -> Collections.sort(t.getComments()));
        return tweets;
    }

    @ModelAttribute("myTweets")
    public List<Tweet> getMyTweets(@AuthenticationPrincipal CurrentUser customUser) {
        List<Tweet> myTweets = tweetRepository.findAllByUserId(customUser.getUser().getId());
        Collections.sort(myTweets);
        myTweets.forEach(t -> Collections.sort(t.getComments()));
        return myTweets;
    }

    @PostMapping("/add/{idpost}")
    public String saveComment(@PathVariable long idpost, @Valid Comment comment, BindingResult bindingResult,
                              @RequestParam String jspAddress, @AuthenticationPrincipal CurrentUser customUser,
                              Model model) {
        if (bindingResult.hasErrors()) {

            return jspAddress;
        }
        comment.setTweet(tweetRepository.getById(idpost));
        comment.setUser(customUser.getUser());

        commentRepository.save(comment);
        return "redirect:" + jspAddress;
    }

}
