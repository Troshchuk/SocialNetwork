package com.bionic.socialNetwork.models;

import javax.persistence.*;

/**
 * GroupPost entity
 *
 * @author yoalex5
 * @version 1.00 16.07.14
 */
@Entity
@Table (name = "Groups_Posts")
public class GroupPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_post_id")
    private long groupPostId;

    @Column(name = "group_id")
    private long groupId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "post")
    private String post;

    public GroupPost() {

    }

    public GroupPost(long groupId, long userId, String post) {
        this.groupId = groupId;
        this.userId = userId;
        this.post = post;
    }

    public long getGroupPostId() {
        return groupPostId;
    }

    public void setGroupPostId(long groupPostId) {
        this.groupPostId = groupPostId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
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
