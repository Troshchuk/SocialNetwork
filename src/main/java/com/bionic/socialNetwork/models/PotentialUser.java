package com.bionic.socialNetwork.models;

import javax.persistence.*;

/**
 * @author yoalex5
 * @version 1.0 18.07.14
 */
@Entity
@Table(name = "Posts")
public class PotentialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "add_user_id")
    private long potentialUserId;

    @Column(name = "Invite")
    private String invite;

    public PotentialUser() {

    }

    public PotentialUser(String invite) {
        this.invite = invite;
    }

    public long getPotentialUserId() {
        return potentialUserId;
    }

    public void setPotentialUserId(long potentialUserId) {
        this.potentialUserId = potentialUserId;
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }
}
