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
    long postId;

    @Column(name = "users_id")
    long userId;

    @Column(name = "Post")
    String post;// **File** maybe

    Post(){

    }

    Post(long postId, long userId, String post){
        this.postId = postId;
        this.userId = userId;
        this.post = post;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
