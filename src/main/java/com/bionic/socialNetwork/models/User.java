package com.bionic.socialNetwork.models;

import javax.persistence.*;
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

    private String position;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Users_Interests",
               joinColumns = {@JoinColumn(name = "user_id")},
               inverseJoinColumns = {@JoinColumn(name = "interest_id")})
    private Set<Interest> interests = new HashSet<Interest>(0);

    @OneToOne
    @JoinColumn(name = "id")
    private Password password;

    @OneToMany(mappedBy = "receiverUser", targetEntity = PrivateMessage.class, fetch = FetchType.EAGER)
    private List<PrivateMessage> privateMessages;

    @OneToMany(mappedBy = "user", targetEntity = Post.class, fetch = FetchType.EAGER)
    private List<Post> posts;

    public User() {

    }

    public User(String login, String name, String surname,
                String position) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.position = position;
    }

    public List<Post> getPosts() {
        return posts;
    }

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

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public List<PrivateMessage> getPrivateMessages() {
        return privateMessages;
    }

    public void setPrivateMessages(List<PrivateMessage> privateMessages) {
        this.privateMessages = privateMessages;
    }
}
