package com.bionic.socialNetwork.models;


//import org.hibernate.annotations.Columns;

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
@Table (name = "Interests")
public class Interests {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "interests_id")
    private long interests_id;

    @Column (name = "interest")
    private String interest;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        public Set<User> getUserSet() {
             return userSet;
        }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    private Set<User> userSet = new HashSet<User>(0);

    Interests() {

    }

    Interests(long intrst_id, String interest) {
        this.interest = interest;
        this.interests_id = intrst_id;
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