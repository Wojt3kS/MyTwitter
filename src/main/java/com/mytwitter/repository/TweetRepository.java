package com.mytwitter.repository;

import com.mytwitter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {

    List<Tweet> findAllByUserId(long id);
    Tweet getById(long id);
}
