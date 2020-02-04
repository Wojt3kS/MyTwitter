package com.mytwitter.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "comments")
public class Comment implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @NotBlank
    @Size(max = 140)
    private String text;
    @CreationTimestamp
    private LocalDateTime created;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Tweet tweet;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFormattedTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return created.format(formatter);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", created=" + created +
                ", tweet=" + tweet +
                ", user=" + user +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Comment toCompare = (Comment) o;
        if (this.created.isBefore(toCompare.created)){
            return -1;
        } else if (!this.created.isBefore(toCompare.created)){
            return 1;
        } else {
            return 0;
        }
    }
}
