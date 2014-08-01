package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.PrivateMessageDao;
import com.bionic.socialNetwork.dao.impl.PrivateMessageDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.PrivateMessage;
import com.bionic.socialNetwork.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Collection;

/**
 * @author Denis Biyovskiy
 * @version 1.00  23.07.2014.
 */
public class SentMessageList {
    private Collection<PrivateMessage> receivedMessages;

    @JsonIgnore
    private boolean resolved;

    @JsonIgnore
    private static final Logger LOGGER = LogManager.getLogger(SentMessageList.class.getName());

    public SentMessageList(long id, int page) {
        PrivateMessageDao privateMessageDao = new PrivateMessageDaoImpl();
        try {
            User user = new UserDaoImpl().selectById(id);
            receivedMessages =
                    privateMessageDao.selectSentNextWith(user, page);
            resolved =true;
        }
        catch (NullPointerException e) {
            resolved = false;
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            resolved = false;
        }
    }

    public Collection<PrivateMessage> getPrivateMessages() {
        return receivedMessages;
    }

    public boolean isResolved() {
        return resolved;
    }
}


