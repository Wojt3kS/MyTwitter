package com.mytwitter.entity;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tweets")
public class Tweet {
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
    private User user;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tweet")
    private List<Comment> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", created=" + created +
                '}';
    }
}