package com.bionic.socialNetwork.models;

import javax.persistence.*;

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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String login;
    private String name;
    private String surname;
    private String position;
    @OneToOne
    @JoinColumn(name = "id")
    private Password password;

    public User() {

    }

    public User(String login, String name, String surname,
                String position) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.position = position;
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
}
