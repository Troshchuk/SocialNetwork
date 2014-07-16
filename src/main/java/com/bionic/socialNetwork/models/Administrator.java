package com.bionic.socialNetwork.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  16.07.14.
 */
@Entity(name = "Administrators")
public class Administrator {
    @Id
    private long id;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    public Administrator() {

    }

    public Administrator(long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
