package com.bionic.socialNetwork.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * GroupPost entity
 *
 * @author yoalex5
 * @version 1.00 16.07.14
 */
@Entity
@Table (name = "Posts_Of_Groups")
public class GroupPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_post_id")
    private long groupPostId;

    @Column(name = "post")
    private String post;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Timestamp time;

    public GroupPost() {

    }

    public GroupPost(Group group, User user,  String post, Timestamp time) {
        this.group = group;
        this.user = user;
        this.post = post;
        this.time = time;
    }

    public long getGroupPostId() {
        return groupPostId;
    }

    public void setGroupPostId(long groupPostId) {
        this.groupPostId = groupPostId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @JsonIgnore
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
