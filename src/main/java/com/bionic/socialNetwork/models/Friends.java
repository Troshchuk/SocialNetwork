package com.bionic.socialNetwork.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Friends Entity
 *
 * @author Matvii Mitnitskyi
 * version 1.00 21.07.2014.
 */

@Entity
@Table (name = "Friends")
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "user_id")
    private long user_id;

    @Column (name = "friend_id")
    private long friend_id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Friends", joinColumns = {@JoinColumn(name = "friend_id")},
            inverseJoinColumns = {@JoinColumn(name ="id")})
    private Set<User> userSet;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(long friend_id) {
        this.friend_id = friend_id;
    }
}
