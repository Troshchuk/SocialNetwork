package com.bionic.socialNetwork.models;

import javax.persistence.*;

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

    public Post() {

    }

    public Post(String post, User user) {
        this.user = user;
        this.post = post;
    }

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
}
