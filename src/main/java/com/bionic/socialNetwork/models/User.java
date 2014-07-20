package com.bionic.socialNetwork.models;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.Session;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
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

    @XmlTransient
    private String login;

    private String name;

    private String surname;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Users_Interests",
               joinColumns = {@JoinColumn(name = "user_id")},
               inverseJoinColumns = {@JoinColumn(name = "interest_id")})
    private Set<Interest> interests = new HashSet<Interest>(0);

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Users_Groups",
               joinColumns = {@JoinColumn(name = "user_id")},
               inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private Set<Group> groups = new HashSet<Group>(0);

    private String position;

    @OneToOne
    @JoinColumn(name = "id")
    private Password password;

    @OneToMany(mappedBy = "user", targetEntity = Post.class, fetch = FetchType.EAGER)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", targetEntity = SessionUser.class, fetch = FetchType.EAGER)
    private List<SessionUser> sessions;

    @OneToMany(mappedBy = "user", targetEntity = GroupPost.class, fetch = FetchType.EAGER)
    private List<GroupPost> groupPosts;

    public User() {

    }

    public User(String login, String name, String surname,
                String position) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.position = position;
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

    @JsonIgnore
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
}
