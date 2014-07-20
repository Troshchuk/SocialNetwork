package com.bionic.socialNetwork.models;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToOne
    @JoinColumn(name = "group_id")
    private GroupPost post;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Users_Groups",
               joinColumns = {@JoinColumn(name = "group_id")},
               inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> userSet = new HashSet<User>(0);

    public Group() {

    }

    public Group(String name) {
        this.name = name;
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

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public GroupPost getPost() {
        return post;
    }

    public void setPost(GroupPost post) {
        this.post = post;
    }
}
