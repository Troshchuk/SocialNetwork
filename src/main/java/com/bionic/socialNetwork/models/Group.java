package com.bionic.socialNetwork.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Group entity
 *
 * @author yoalex5
 * @version 1.00 16.07.14
 */
@Entity
@Table(name = "Groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id")
    private long groupId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "group", targetEntity = GroupPost.class, fetch = FetchType.LAZY)
    private List<GroupPost> groupPosts;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Users_Groups",
               joinColumns = {@JoinColumn(name = "group_id")},
               inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> followers = new HashSet<User>(0);

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    public Group() {

    }

    public Group(String name, String description, User author) {
        this.name = name;
        this.author = author;
        this.description = description;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> userSet) {
        this.followers = userSet;
    }

    @JsonIgnore
    public List<GroupPost> getGroupPosts() {
        return groupPosts;
    }

    public void setGroupPosts(List<GroupPost> groupPosts) {
        this.groupPosts = groupPosts;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthorId(User author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
