package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.GroupDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.GroupDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.models.User;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CreateGroupLogic
 *
 * @author Matvey Mitnitskyi
 * @version 1.01 31.07.14.
 */

public class CreateGroupLogic {
    @JsonIgnore
    private Group group = new Group();

    private String response;

     public CreateGroupLogic(String name, String description, long user_id) {
        try {
            UserDao userDao = new UserDaoImpl();
            User user = userDao.selectById(user_id);
            this.group.setName(name);
            this.group.setDescription(description);
            this.group.setAuthorId(user);
            if (Match(name)) {
                boolean isCreated = addGroup(group);
                response = "{\"isCreated\": " + "\"" + isCreated + "\"}";
            }
            else  { response = "{\"groupName\": "
                               + "\"groupName is incorrect\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addGroup(Group group) {
        try {

            GroupDao groupDao = new GroupDaoImpl();
            groupDao.insert(group);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Match (String name) {

        Pattern namePattern = Pattern.compile("([\\w]|[\\s])+");
        Matcher matcher = namePattern.matcher(name);
        return matcher.matches();
    }

    public String getResponse() { return response; }

    public Group getGroup() { return group; }
}