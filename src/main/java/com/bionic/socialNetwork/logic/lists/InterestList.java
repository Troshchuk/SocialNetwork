package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.InterestDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.InterestDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Interest;
import com.bionic.socialNetwork.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Collection;
import java.util.List;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  21.07.14.
 */
public class InterestList {
    private Collection<Interest> interests;

    @JsonIgnore
    private boolean resolved;

    @JsonIgnore
    private static final Logger LOGGER =
            LogManager.getLogger(InterestList.class.getName());

    public InterestList(long id) {
        try {
            UserDao userDao = new UserDaoImpl();
            interests = userDao.selectAllInterests(id);
            resolved = true;
        } catch (NullPointerException e) {
            resolved = false;
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            resolved = false;
        }
    }

    public Collection<Interest> getInterests() {
        return interests;
    }

    public boolean isResolved() {
        return resolved;
    }
}
