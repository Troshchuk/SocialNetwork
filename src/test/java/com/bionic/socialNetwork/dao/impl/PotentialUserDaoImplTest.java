package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PotentialUserDao;
import com.bionic.socialNetwork.models.PotentialUser;
import org.junit.After;
import org.junit.Before;

/**
 * @author yoalex5
 * @version 1.0 18.07.14
 */
public class PotentialUserDaoImplTest {

    private PotentialUser potentialUser;
    private PotentialUserDao potentialUserDao;

    @Before
    public void beforeTest() throws Exception {
        potentialUser = new PotentialUser("YOYOYO");
        potentialUserDao = new PotentialUserDaoImpl();
        potentialUserDao.insert(potentialUser);
    }

    @After
    public void afterTest() throws Exception {
        potentialUserDao.delete(potentialUser);
    }
}
