package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.lists.UserList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  04.08.14.
 */

@Path("interest{interest}")
public class InterestsController {
    @GET
    @Path("{page}")
    public UserList search(@PathParam("interest") String interest,
                           @PathParam("page") int page) {
        UserList userList = new UserList();
        userList.usersByInterest(interest, page);
        return new UserList();
    }
}
