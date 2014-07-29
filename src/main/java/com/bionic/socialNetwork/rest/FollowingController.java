package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.lists.FollowingUsersList;
import com.bionic.socialNetwork.logic.lists.FollowingUsersListByName;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by Denis on 28.07.2014.
 */

@Path("following{id}")
public class FollowingController {


    @GET
    @Path("getPage{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public FollowingUsersList getFollowingUser(@PathParam("id") long id,
                                               @PathParam("page") int page) {
        return new FollowingUsersList(id, page);
    }

    @GET
    @Path("getFollowingByName{name}/{surname}/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public FollowingUsersListByName getFollowingsByName(@Context HttpServletRequest request,
                                 @PathParam("name") String name,
                                 @PathParam("surname") String surname,
                                 @PathParam("page") int number) {
        FollowingUsersListByName followingUsersListByName = new FollowingUsersListByName(name, surname, Long.parseLong(request.getParameter("userId")), number * 10);
        return followingUsersListByName;
    }

}
