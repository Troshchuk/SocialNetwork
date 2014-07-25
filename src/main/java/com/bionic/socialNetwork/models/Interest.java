package com.bionic.socialNetwork.models;


//import org.hibernate.annotations.Columns;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;

/**
 * Interests Entity
 *
 * @ author Matvii Mitnitskyi
 * @ version 1.00   16.08.2014
 */


@Entity
@Table(name = "Interests")
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "interest_id")
    private long interests_id;

    @Column(name = "interest")
    private String interest;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Users_Interests",
               joinColumns = {@JoinColumn(name = "interest_id")},
               inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users = new HashSet<User>(0);

    public Interest() {

    }

    public Interest(long intrst_id, String interest) {
        this.interest = interest;
        this.interests_id = intrst_id;
    }

    @JsonIgnore
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> userSet) {
        this.users = userSet;
    }

    public void setInterests_id(long interests_id) {
        this.interests_id = interests_id;
    }

    public String setInterest() {
        return interest;
    }

    public String getInterest() {
        return interest;
    }

    public long getInterests_id() {
        return interests_id;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }


}