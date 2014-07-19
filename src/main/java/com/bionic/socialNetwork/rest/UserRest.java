package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.Login;
import com.bionic.socialNetwork.logic.Registration;
import com.bionic.socialNetwork.logic.SessionController;
import com.bionic.socialNetwork.models.SessionUser;
import com.bionic.socialNetwork.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by Bish_ua on 16.07.2014.
 */


// not finished
@Path("user")
public class UserRest {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    @Path("login")
    public String login(@Context HttpServletRequest request, @FormParam("login") String login, @FormParam("pass") String password) {
        HttpSession session = request.getSession();
        Login log = new Login();
        User user = log.getUser(login, password);

        if(user != null){
            SessionController sessionController = new SessionController();
            session.setAttribute("user", sessionController.getNewSession(user));

            return "{\"status\": true}";
        }else{
            return "{\"status\": false}";
        }

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    @Path("registration")
    public String registration(@FormParam("login") String login, @FormParam("password") String password,
                               @FormParam("name") String name, @FormParam("surname") String surname) {

        Registration registration = new Registration();


        if(registration.addUser(login,password,name,surname)){
            return "{\"status\": true}";
        }else {
            return "{\"status\": false}";
        }


    }
 }
