package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.PrivateMessageDao;
import com.bionic.socialNetwork.dao.impl.PrivateMessageDaoImpl;
import com.bionic.socialNetwork.models.PrivateMessage;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Collection;
import java.util.List;

/**
 * @author Denis Biyovskiy
 * @version 1.00  23.07.2014.
 */
public class PrivateMessageList {
    @JsonIgnore
    private int beginId;
    @JsonIgnore
    private PrivateMessageDao privateMessageDao;

    private Collection<PrivateMessage> privateMessages;

    public PrivateMessageList(int beginId) {
        this.beginId = beginId;
        privateMessageDao = new PrivateMessageDaoImpl();
    }

    public void next() {
        try {
            List<PrivateMessage> privateMessageList = privateMessageDao.selectNextReceiverId(beginId);
            beginId += privateMessageList.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


