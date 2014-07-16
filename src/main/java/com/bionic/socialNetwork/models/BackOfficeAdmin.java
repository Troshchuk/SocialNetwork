package com.bionic.socialNetwork.models;

import javax.persistence.*;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  16.07.14.
 */
@Entity
@Table(name = "Back_Office_Administrators")
public class BackOfficeAdmin {
    @Id
    private long id;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    public BackOfficeAdmin() {

    }

    public BackOfficeAdmin(long id) {
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
