package com.mytwitter.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {
    private final com.mytwitter.entity.User user;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       com.mytwitter.entity.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public com.mytwitter.entity.User getUser() {
        return user;
    }
}
