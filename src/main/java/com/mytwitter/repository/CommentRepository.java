package com.mytwitter.repository;

import com.mytwitter.entity.Comment;
import com.mytwitter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Transactional
    @Modifying
    void deleteAllByTweet(Tweet tweet);
}
