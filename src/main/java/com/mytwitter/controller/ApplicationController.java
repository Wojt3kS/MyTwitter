package com.mytwitter.controller;

import com.mytwitter.entity.Comment;
import com.mytwitter.entity.Message;
import com.mytwitter.entity.Tweet;
import com.mytwitter.entity.User;
import com.mytwitter.repository.MessageRepository;
import com.mytwitter.repository.TweetRepository;
import com.mytwitter.repository.UserRepository;
import com.mytwitter.service.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/application")
public class ApplicationController {

    private final TweetRepository tweetRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public ApplicationController(TweetRepository tweetRepository, MessageRepository messageRepository,
                                 UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute("user")
    public User getUser(@AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();

        return user;
    }

    @ModelAttribute("comment")
    public Comment getComment(@AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();
        Comment comment = new Comment();
        comment.setUser(user);
        return comment;
    }

    @ModelAttribute("tweet")
    public Tweet getTweet(@AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();
        Tweet tweet = new Tweet();
        tweet.setUser(user);
        return tweet;
    }

    @ModelAttribute("message")
    public Message getMessage(@AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();
        Message message = new Message();
        message.setFromUser(user);
        return message;
    }

    @ModelAttribute("users")
    public List<User> getAllUsers(){
        List<User> allUsers = userRepository.findAll();
        return allUsers;
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


    @GetMapping("")
    public String home() {
        return "application/home";
    }

    @GetMapping("/tweets")
    public String getTweetsList() {
        return "application/tweets";
    }

    @GetMapping("/my-tweets")
    public String getMyTweetsList() {
        return "application/my-tweets";
    }

    @GetMapping("/messages")
    public String getMessages(Model model, @AuthenticationPrincipal CurrentUser customUser) {
        List<Message> currentUserMessages = messageRepository.findAllByToUser(customUser.getUser());
        currentUserMessages.forEach(m -> {
            if (m.getText().length() > 30) {
                m.setText(m.getText().substring(0, 29) + "...");
            }
        });
        Collections.sort(currentUserMessages);
        model.addAttribute("messages", currentUserMessages);
        return "application/messages";
    }


}
