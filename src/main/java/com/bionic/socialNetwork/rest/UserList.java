package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.Login;
import com.bionic.socialNetwork.logic.UsersList;
import com.bionic.socialNetwork.models.User;

import javax.ws.rs.*;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

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
    public User test() {
//        HttpSession session = request.getSession();
//        session.setAttribute();


        UsersList userList = new UsersList();
        List<User> users = userList.getUserList();
        System.out.println(users);
        return userList.getUserList().get(0);

    }
}
