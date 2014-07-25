package com.bionic.socialNetwork.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Posts entity
 *
 * @author Denis Biyovskiy
 * @version 1.00  15.07.2014.
 */
@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private long postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name = "Post")
    private String post;

    private Timestamp time;

    public Post() {

    }

    public Post(String post, User user, Timestamp time) {
        this.time = time;
        this.user = user;
        this.post = post;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
