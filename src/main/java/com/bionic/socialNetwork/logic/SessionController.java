package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.SessionUserDao;
import com.bionic.socialNetwork.dao.impl.SessionUserDaoImpl;
import com.bionic.socialNetwork.models.SessionUser;
import com.bionic.socialNetwork.models.User;

/**
 * Created by Bish_ua on 18.07.2014.
 */
public class SessionController {


    public SessionUser getNewSession(User user){



        SessionUserDao sessionDao = new SessionUserDaoImpl();
        SessionUser sessionUser = new SessionUser("defaultSession", user);


        try {
            sessionDao.insert(sessionUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sessionDao.selectById(sessionUser.getSessionId());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return sessionUser;
    }


}
