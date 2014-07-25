package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.lists.*;
import com.bionic.socialNetwork.logic.UserLogic;
import com.bionic.socialNetwork.models.User;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 * @author Dmytro Troshchuk, Igor Kozhevnikov, Denis Biyovskiy
 * @version 1.00  18.07.14.
 */


@Path("user{id}")
public class UserController {
    @GET
    @Path("interests")
    @Produces(MediaType.APPLICATION_JSON)
    public InterestList getInterests(@PathParam("id") long id) {
        return new InterestList(id);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getPage(@Context ServletContext context) {
        return context.getResourceAsStream("/WEB-INF/pages/user.html");


    }

    @GET
    @Path("getUser")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") long id) {
        return new UserLogic().getUser(id);
    }

    @POST
    @Path("createPost")
    @Produces(MediaType.APPLICATION_JSON)
    public String addPost(@Context HttpServletRequest request,
                          @FormParam("msg") String msg) {
        return "{\"status\": " + new UserLogic()
                .createPost((Long) request.getAttribute("userId"), msg) + "}";
    }

    @POST
    @Path("deletePost")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePost(@Context HttpServletRequest request,
                             @FormParam("postId") long postId) {
        return "{\"status\": " + new UserLogic()
                .deletePost((Long) request.getAttribute("userId"), postId) +
               "}";
    }

    @GET
    @Path("posts{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public PostsList getPosts(@PathParam("id") long id,
                              @PathParam("page") int page) {
        return new PostsList(id, page);

    }

    @GET
    @Path("followings{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public FollowingUsersList getFollowingUser(@PathParam("id") long id,
                                               @PathParam("page") int page) {

        return new FollowingUsersList(id, page);

    }

    @GET
    @Path("groups{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserGroupsList getGroups(@PathParam("id") long id,
                                    @PathParam("page") int page) {
        return new UserGroupsList(id, page);
    }

    @GET
    @Path("exit")
    @Produces(MediaType.APPLICATION_JSON)
    public String exit(@Context HttpServletResponse response) {
        Cookie cookie = new Cookie("sessionId", "0");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        Cookie userIdCookie = new Cookie("userId", "0");
        userIdCookie.setPath("/");
        userIdCookie.setMaxAge(0);
        response.addCookie(userIdCookie);
        return "{\"status\": true}";
    }
}
