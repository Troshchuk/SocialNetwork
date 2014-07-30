package com.bionic.socialNetwork.models;


import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User's entity
 *
 * @author Dmytro Troshchuk
 * @version 1.00  14.07.14.
 */
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String login;

    private String name;

    private String surname;

    private Date birthday;

    @Column(name = "avatar", nullable = false)
    private String pathToAvatar;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Followings", joinColumns = {@JoinColumn(name = "follower_id")},
            inverseJoinColumns = {@JoinColumn(name = "following_id")})
    private Set<User> followings = new HashSet<User>(0);

    @ManyToMany(mappedBy = "followings")
    private Set<User> myFollowings = new HashSet<User>(0);



    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Users_Interests",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "interest_id")})
    private Set<Interest> interests = new HashSet<Interest>(0);

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Users_Groups",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private Set<Group> groups = new HashSet<Group>(0);

    private String position;

    @OneToOne
    @JoinColumn(name = "id")
    private Password password;

    @OneToMany(mappedBy = "user", targetEntity = Post.class, fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", targetEntity = SessionUser.class, fetch = FetchType.LAZY)
    private List<SessionUser> sessions;

    @OneToMany(mappedBy = "user", targetEntity = GroupPost.class, fetch = FetchType.LAZY)
    private List<GroupPost> groupPosts;

    @OneToMany(mappedBy = "author", targetEntity = Group.class, fetch = FetchType.LAZY)
    private List<Group> createdGroups;

    public User() {

    }

    public User(String login, String name, String surname,
                String position, Date birthday) {
        this.birthday = birthday;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.pathToAvatar = "/avatars/noavatar.png";
    }

    @JsonIgnore
    public List<Post> getPosts() {
        return posts;
    }

    @JsonIgnore
    public Set<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Set<Interest> interests) {
        this.interests = interests;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @JsonIgnore
    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    @JsonIgnore
    public List<SessionUser> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionUser> sessions) {
        this.sessions = sessions;
    }

    @JsonIgnore
    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @JsonIgnore
    public Set<User> getFollowings() {
        return followings;
    }

    public void setFollowings(Set<User> followings) {
        this.followings = followings;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @JsonIgnore
    public String getPathToAvatar() {
        return pathToAvatar;
    }

    public void setPathToAvatar(String pathToAvatar) {
        this.pathToAvatar = pathToAvatar;
    }

    @JsonIgnore
    public List<GroupPost> getGroupPosts() {
        return groupPosts;
    }

    public void setGroupPosts(List<GroupPost> groupPosts) {
        this.groupPosts = groupPosts;
    }

    @JsonIgnore
    public List<Group> getCreatedGroups() {
        return createdGroups;
    }

    public void setCreatedGroups(List<Group> createdGroups) {
        this.createdGroups = createdGroups;
    }
}
