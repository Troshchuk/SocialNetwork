package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.SessionUserDao;
import com.bionic.socialNetwork.dao.impl.SessionUserDaoImpl;
import com.bionic.socialNetwork.models.SessionUser;
import com.bionic.socialNetwork.models.User;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Bish_ua on 18.07.2014.
 */
public class SessionController {
    public String getNewSession(User user) {
        SessionUserDao sessionDao = new SessionUserDaoImpl();
        String session  = generateSession();
        SessionUser sessionUser = new SessionUser(session, user);

        try {
            sessionDao.insert(sessionUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return session;
    }

    /**
     *
     *
     * @param session
     * @return
     */
    public long verifySession(String session) {
        SessionUser sessionUser = null;
        SessionUserDao sessionUserDao = new SessionUserDaoImpl();
        try {
            sessionUser = sessionUserDao.selectBySession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sessionUser != null) {
            return sessionUser.getUser().getId();
        } else {
            return -1;
        }
    }

    private String generateSession() {
        return RandomStringUtils.random(45, true, true);
    }
}
