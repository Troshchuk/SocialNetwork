package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.UsersList;
import com.bionic.socialNetwork.models.User;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  18.07.14.
 */
@Path("user2")
public class UserList {
    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
//    public String test(@Context HttpServletRequest request) {
    public ListOfUsers getNextUsers() {
//        HttpSession session = request.getSession();
//        session.setAttribute();

        Collection<User> users = new UsersList().getUserList();
        ListOfUsers userList = new ListOfUsers(users);

        return userList;



    }

    class ListOfUsers {
        Collection<User> users;

        public ListOfUsers(Collection users) {
            this.users = users;
        }

        public Collection<User> getUsers() {
            return users;
        }
    }
}
