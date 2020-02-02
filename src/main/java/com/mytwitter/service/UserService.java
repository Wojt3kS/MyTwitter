package com.mytwitter.service;

import com.mytwitter.entity.User;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);
}