package com.bionic.socialNetwork.models;

import javax.persistence.*;

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

    public PrivateMessage() {

    }

    public PrivateMessage(User sentUser, User receiverUser, String message) {
        this.sentUser = sentUser;
        this.receiverUser = receiverUser;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
