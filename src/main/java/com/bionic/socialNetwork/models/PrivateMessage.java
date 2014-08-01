package com.bionic.socialNetwork.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Private messages entity
 *
 * @author Denis Biyovskiy
 * @version 1.00  15.07.2014.
 */
@Entity
@Table(name = "Private_Messages")
public class PrivateMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private long messageId;

    @ManyToOne
    @JoinColumn(name = "sent_user_id")
    private User sentUser;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id")
    private User receiverUser;

    private String message;

    private Timestamp time;

    @Column(name = "`read`")
    private boolean read;

    public PrivateMessage() {

    }

    public PrivateMessage(User sentUser, User receiverUser, String message,
                          Timestamp time) {
        this.time = time;
        this.sentUser = sentUser;
        this.receiverUser = receiverUser;
        this.message = message;
        this.read = false;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }


    public User getSentUser() {
        return sentUser;
    }

    public void setSentUser(User sentUser) {
        this.sentUser = sentUser;
    }


    public User getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(User receiverUser) {
        this.receiverUser = receiverUser;
    }

    @JsonIgnore
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public boolean getRead() { return read; }

    public void setRead(boolean read) { this.read = read; }
}
