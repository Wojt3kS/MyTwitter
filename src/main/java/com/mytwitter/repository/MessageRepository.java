package com.mytwitter.repository;

import com.mytwitter.entity.Message;
import com.mytwitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByFromUser(User user);
    List<Message> findAllByToUser(User user);
    Message getById(long id);
}
