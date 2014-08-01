package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.GroupDao;
import com.bionic.socialNetwork.dao.PasswordDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.GroupDaoImpl;
import com.bionic.socialNetwork.dao.impl.PasswordDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Test for CreateGroupLogic
 * @author Matvey Mitnitskyi
 * @version 1.00 30.07.14.
 */

public class CreateGroupLogicTest {
    private Group group;
    private long group_id;
    private UserDao userDao;
    private CreateGroupLogic createGroupLogic;
    private User user;
    private String login = "matvey@matvey.com";
    private GroupDao groupDao;

    @Before
    public void insertGroupTest() throws Exception {
            userDao = new UserDaoImpl();
            user = new User(login, "Mavey", " "," ", new java.sql.Date(0));
            userDao.insert(user, new Password("password"));
    }

    @Test
    public void createGroupTest() throws Exception{
        groupDao = new GroupDaoImpl();
        /**
         * CreateGroupLogic creates group using 3 name, description & authors Id
         * createGroupLogic includes the groupDao.insert(Group group) method
         */
        createGroupLogic = new CreateGroupLogic("Group name",
                                                "Group description", user.getId());
        group = createGroupLogic.getGroup();
        group_id = group.getGroupId();

        assertNotNull(group);
//        System.out.print(createGroupLogic.getResponse());
        assertEquals("{\"status\": \"true\"}",createGroupLogic.getResponse());
        assertEquals( user.getId() , group.getAuthor().getId());

        groupDao.delete(group);
    }

    @After
    public void deleteGroup() throws Exception{

        new UserDaoImpl().delete(user);
    }
}
