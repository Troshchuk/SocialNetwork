package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.logic.UserLogic;
import com.bionic.socialNetwork.logic.lists.FollowingUsersList;
import com.bionic.socialNetwork.logic.lists.FollowingUsersListByName;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by Denis Biyovskiy on 28.07.2014.
 */

@Path("following{id}")
public class FollowingController {

    @POST
    @Path("subscribe{id}/{following_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean subscribeOnGroup(@PathParam("id") long id,
                                    @PathParam("following_id") long followingId) {
        return new UserLogic().subscribeOnUser(id, followingId);
    }

    @GET
    @Path("unsubscribe{id}/{following_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean unsubscribeUser(@PathParam("id") long id,
                                   @PathParam("following_id") long followingId) {
        return new UserLogic().unsubscribeUser(id, followingId);
    }


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
        FollowingUsersListByName followingUsersListByName =
                new FollowingUsersListByName(name, surname,
                        Long.parseLong(request.getParameter("userId")), number * 10);
        return followingUsersListByName;
    }

}
