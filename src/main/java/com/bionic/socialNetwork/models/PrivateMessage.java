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

    @Column(name = "sent_user_id")
    private long sentUserId;

    @Column(name = "receiver_user_id")
    private long receiverUserId;

    private String message;//or maybe **File**

    PrivateMessage() {

    }

    PrivateMessage(long messageId, long sentUserId,
                   long receiverUserId, String message) {
        this.messageId = messageId;
        this.sentUserId = sentUserId;
        this.receiverUserId = receiverUserId;
        this.message = message;
    }


    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getSentUserId() {
        return sentUserId;
    }

    public void setSentUserId(long sentUserId) {
        this.sentUserId = sentUserId;
    }

    public long getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(long receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
