package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.InterestDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.InterestDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Interest;
import com.bionic.socialNetwork.models.User;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Collection;
import java.util.List;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  21.07.14.
 */
public class InterestList {
    private Collection<Interest> interests;

    public InterestList(long id) {
        try {
            UserDao userDao = new UserDaoImpl();
            interests = userDao.selectAllInterests(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<Interest> getInterests() {
        return interests;
    }
}
