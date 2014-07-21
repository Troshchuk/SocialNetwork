package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.Login;
import com.bionic.socialNetwork.logic.Registration;
import com.bionic.socialNetwork.logic.SessionController;
import com.bionic.socialNetwork.logic.UsersList;
import com.bionic.socialNetwork.models.SessionUser;
import com.bionic.socialNetwork.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * @author Dmytro Troshchuk, Igor Kozhevnikov
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
        }else{
                return "{\"status\": noInvite}";
        }


    }

    @GET
    @Path("workers")
    @Produces(MediaType.APPLICATION_JSON)
//    public String test(@Context HttpServletRequest request) {
    public ListOfUsers getNextUsers(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionUser = (String) session.getAttribute("user");
        SessionController sessionController = new SessionController();
        long userId = sessionController.verifySession(sessionUser);
        if (userId != -1) {
            Collection<User> users = new UsersList().getUserList();
            ListOfUsers userList = new ListOfUsers(users);
            return userList;
        } else {
            return null;
        }
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
