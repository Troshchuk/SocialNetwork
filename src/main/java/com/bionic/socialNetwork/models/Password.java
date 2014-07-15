package com.bionic.socialNetwork.models;

import javax.persistence.*;

/**
 * Password entity
 *
 * @author Dmytro Troshchuk
 * @version 1.00  14.07.14.
 */
@Entity
@Table(name = "Passwords")
public class Password {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    private String password;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Password() {

    }

    public Password(long userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
