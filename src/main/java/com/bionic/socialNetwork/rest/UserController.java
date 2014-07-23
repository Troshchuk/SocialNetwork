package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.dao.PostDao;
import com.bionic.socialNetwork.dao.impl.PostDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.logic.*;
import com.bionic.socialNetwork.models.Interest;
import com.bionic.socialNetwork.models.Post;
import com.bionic.socialNetwork.models.SessionUser;
import com.bionic.socialNetwork.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Set;

/**
 * @author Dmytro Troshchuk, Igor Kozhevnikov, Denis Biyovskiy
 * @version 1.00  18.07.14.
 */


// not finished
@Path("user")
public class UserController {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    @Path("login")
    public String login(@Context HttpServletRequest request,
                        @FormParam("login") String login,
                        @FormParam("pass") String password) {
        HttpSession session = request.getSession();
        Login log = new Login();
        User user = log.getUser(login, password);

        if (user != null) {
            SessionController sessionController = new SessionController();
            session.setAttribute("user", sessionController.getNewSession(user));

            return "{\"status\": true}";
        } else {
            return "{\"status\": false}";
        }

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    @Path("registration")
    public String registration(@FormParam("name") String name,
                               @FormParam("surname") String surname,
                               @FormParam("email") String login,
                               @FormParam("password") String password,
                               @FormParam("invite") String invite) {

        Registration registration = new Registration();

        if (registration.checkInviteCode(invite)) {
            if (registration.addUser(name, surname, login, password)) {
                return "{\"status\": true}";
            } else {
                return "{\"status\": false}";
            }
        } else {
            return "{\"status\": noInvite}";
        }
    }

    @GET
    @Path("workers{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserList getNextUsers(@Context HttpServletRequest request,
                                 @PathParam("number") long number) {
        HttpSession session = request.getSession();
        String sessionUser = (String) session.getAttribute("user");
        SessionController sessionController = new SessionController();
        long userId = sessionController.verifySession(sessionUser);
        if (userId != -1) {
            UserList userList = new UserList(number * 10);
            userList.next();
            return userList;
        } else {
            return null;
        }
    }

    @GET
    @Path("interests{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public InterestList getInterests(@Context HttpServletRequest request,
                                     @PathParam("id") long id) {
        HttpSession session = request.getSession();
        String sessionUser = (String) session.getAttribute("user");
        SessionController sessionController = new SessionController();
        long userId = sessionController.verifySession(sessionUser);
        if (userId != -1) {
            InterestList interestList = new InterestList(id);
            return interestList;
        } else {
            return null;
        }
    }

    @GET
    @Path("id{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@Context HttpServletRequest request,
                        @PathParam("id") long id) {

        HttpSession session = request.getSession();
        String sessionUser = (String) session.getAttribute("user");
        SessionController sessionController = new SessionController();
        long userId = sessionController.verifySession(sessionUser);

        if (userId != -1) {
            User user = null;
            try {
                user = new UserDaoImpl().selectById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return user;
        } else {
            return null;
        }
    }

    @POST
    @Path("post")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addPost(@Context HttpServletRequest request,
                           @PathParam("add_post") String addPost,
                           @PathParam("id") long id) throws Exception {
        HttpSession session = request.getSession();
        String sessionUser = (String) session.getAttribute("user");
        SessionController sessionController = new SessionController();
        long userId = sessionController.verifySession(sessionUser);
        if (userId != -1) {
            PostDao postDao = new PostDaoImpl();
            Post post = new Post(addPost, new UserDaoImpl().selectById(id));
            postDao.insert(post);
            return true;
        } else {
            return false;
        }
    }

    @GET
    @Path("post")
    @Produces(MediaType.APPLICATION_JSON)
    public PostsList getPosts(@Context HttpServletRequest request,
                              @PathParam("number") int number) {
        HttpSession session = request.getSession();
        String sessionUser = (String) session.getAttribute("user");
        SessionController sessionController = new SessionController();
        long userId = sessionController.verifySession(sessionUser);
        if (userId != -1) {
            PostsList postsList = new PostsList(number * 10);
            postsList.next();
            return postsList;
        } else {
            return null;
        }
    }

    @GET
    @Path("following{id}/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public FollowingUsersSet getFollowingUser(@Context HttpServletRequest request,
                                              @PathParam("number") int number,
                                              @PathParam("id") int id) {
        HttpSession session = request.getSession();
        String sessionUser = (String) session.getAttribute("user");
        SessionController sessionController = new SessionController();
        long userId = sessionController.verifySession(sessionUser);
        if (userId != -1) {
            FollowingUsersSet followingUsersSet = new FollowingUsersSet(number , id);
            followingUsersSet.next();
            return followingUsersSet;
        } else {
            return null;
        }
    }

    @GET
    @Path("received_messages{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public PrivateMessageList getPrivateMessage(@Context HttpServletRequest request,
                                                @PathParam("user_id") int id,
                                                @PathParam("number") int number) {
        HttpSession session = request.getSession();
        String sessionUser = (String) session.getAttribute("user");
        SessionController sessionController = new SessionController();
        long userId = sessionController.verifySession(sessionUser);
        if (userId != -1) {
            PrivateMessageList privateMessageList = new PrivateMessageList(number, id);
            privateMessageList.next();
            return privateMessageList;
        } else {
            return null;
        }
    }

    //Create empty rest for News, Followings, Private message, Groups
    @GET
    @Path("news")
    @Produces(MediaType.APPLICATION_JSON)
    public PostsList getNews(@Context HttpServletRequest request,
                             @PathParam("number") int number) {
        return null;
    }

    @GET
    @Path("home")
    @Produces(MediaType.APPLICATION_JSON)
    public PostsList getHomePage(@Context HttpServletRequest request,
                                 @PathParam("number") int number) {
        return null;
    }

    @GET
    @Path("workers")
    @Produces(MediaType.APPLICATION_JSON)
    public PostsList getWorkers(@Context HttpServletRequest request,
                                @PathParam("number") int number) {
        return null;
    }

    @GET
    @Path("groups")
    @Produces(MediaType.APPLICATION_JSON)
    public PostsList getGroups(@Context HttpServletRequest request,
                               @PathParam("number") int number) {
        return null;
    }

    @GET
    @Path("exit")
    @Produces(MediaType.APPLICATION_JSON)
    public String exit(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionUser = (String) session.getAttribute("user");
        SessionController sessionController = new SessionController();
        sessionController.deleteSession(sessionUser);
        return "";
    }
}
