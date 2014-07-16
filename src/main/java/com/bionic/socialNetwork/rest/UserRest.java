package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.Login;
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
            return str+ " " +"yes";
        }else{
            return str + " " +"no";
        }

    }

}
