package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.Login;
import com.bionic.socialNetwork.logic.Registration;
import com.bionic.socialNetwork.models.User;
import javax.ws.rs.*;

/**
 * Created by Bish_ua on 16.07.2014.
 */


// not finished
@Path("user")
public class UserRest {


    @POST
    @Produces("application/xml")
    @Consumes("application/x-www-form-urlencoded")
    @Path("login")
    public String login(@FormParam("login") String login, @FormParam("password") String password) {
        Login log = new Login();
        User user = log.getUser(login, password);
        String str = login +" " + password;
        if(user != null){
            return str+ " " +"that user exist";
        }else{
            return str + " " +"that user not exist";
        }

    }

    @POST
    @Produces("text/plain")
    @Consumes("application/x-www-form-urlencoded")
    @Path("registration")
    public String registration(@FormParam("login") String login, @FormParam("password") String password,
                               @FormParam("surname") String surname, @FormParam("name") String name) {

        Registration registration = new Registration();


        if(registration.addUser(login,password,name,surname)){
            return "reg ok";
            }else {
            return "reg not ok";
        }


        }
    }


