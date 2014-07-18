package com.bionic.socialNetwork.models;

import javax.persistence.*;

/**
 * Add_Users entity
 *
 * @author Denis
 * @version 1.00  18.07.2014.
 */
@Entity
@Table(name = "Add_Users")
public class AddUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "add_user_id")
    private long addUserId;

    @Column(name = "Invite")
    private String invite;

    public AddUser() {

    }

    public AddUser(String invite) {
        this.invite = invite;
    }

    public long getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(long addUserId) {
        this.addUserId = addUserId;
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }
}
