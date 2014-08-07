package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.GroupLogic;
import com.bionic.socialNetwork.logic.lists.GroupPostList;
import com.bionic.socialNetwork.models.Group;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  23.07.14.
 */
@Path("group{id}")
public class GroupController {
    @Context
    private ServletContext context;
    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getPage(@Context HttpServletRequest request,
                               @PathParam("id") long id) {
        return context.getResourceAsStream("/WEB-INF/pages/group.html");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("description")
    public Group getDescription(@PathParam("id") long id) {
        return new GroupLogic().getGroup(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("posts{page}")
    public GroupPostList getPosts(@PathParam("id") long id,
                                  @PathParam("page") int page) {
        return new GroupPostList(id, page);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("createPost")
    public String createPost(@Context HttpServletRequest request,
                              @PathParam("id") long id,
                              @FormParam("msg") String msg) {
        return "{\"status\": " + new GroupLogic()
                .createPost(id, (Long) request.getAttribute("userId"), msg) + "}";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletePost")
    public String deletePost(@Context HttpServletRequest request,
                              @PathParam("id") long id,
                              @FormParam("postId") long postId) {
        return "{\"status\": " + new GroupLogic()
                .deletePost((Long) request.getAttribute("userId"), postId) + "}";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("follow")
    public String follow(@Context HttpServletRequest request,
                             @PathParam("id") long id) {
        return "{\"status\": " + new GroupLogic()
                .follow((Long) request.getAttribute("userId"), id) + "}";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("unfollow")
    public String unFollow(@Context HttpServletRequest request,
                         @PathParam("id") long id) {
        return "{\"status\": " + new GroupLogic()
                .unFollow((Long) request.getAttribute("userId"), id) + "}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("isFollowing")
    public String isFollowing(@Context HttpServletRequest request,
                              @PathParam("id") long id) {
        return "{\"isFollowing\": " + new GroupLogic()
                .isFollowing((Long) request.getAttribute("userId"), id) +
               "}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("members")
    public String members(@PathParam("id") long id) {
        return "{\"members\": " + new GroupLogic()
                .members(id) + "}";
    }
}
