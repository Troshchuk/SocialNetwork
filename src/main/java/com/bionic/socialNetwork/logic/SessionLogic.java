package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.SessionUserDao;
import com.bionic.socialNetwork.dao.impl.SessionUserDaoImpl;
import com.bionic.socialNetwork.models.SessionUser;
import com.bionic.socialNetwork.models.User;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Bish_ua, Troshchuk Dmitry
 * @version 1.01 18.07.2014.
 */
public class SessionLogic {
    public String getNewSession(User user) {
        SessionUserDao sessionDao = new SessionUserDaoImpl();
        String session = generateSession();
        SessionUser sessionUser = new SessionUser(session, user);

        try {
            sessionDao.insert(sessionUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return session;
    }

    /**
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

    public void deleteSession(String session) {
        SessionUser sessionUser = null;
        SessionUserDao sessionUserDao = new SessionUserDaoImpl();
        try {
            sessionUser = sessionUserDao.selectBySession(session);
            if (sessionUser != null) {
                sessionUserDao.delete(sessionUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateSession() {
        return RandomStringUtils.random(45, true, true);
    }
}
