package com.bionic.socialNetwork.models;

import javax.persistence.*;

/**
 * Created by Теплая on 18.07.2014.
 */

@Entity
@Table(name = "Sessions")
public class SessionUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "session_id")
    private long sessionId;


    private String session;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SessionUser() {

    }

    public SessionUser(String session, User user) {
        this.user = user;
        this.session = session;
    }


    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }
}
