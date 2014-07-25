package com.bionic.socialNetwork.rest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.Collection;

import com.bionic.socialNetwork.logic.lists.NewsList;
import com.bionic.socialNetwork.models.Post;

/**
 * rest class for News
 *
 *
 * @ author Matvey Mitnitskyi
 * @ version 1.00  23.07.14
 */

@Path("news")
public class NewsController {
    @Context
    private ServletContext cotext;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getNewsPage(@Context HttpServletRequest request) {

        Long UserId = (Long) request.getAttribute("userId");

        if (UserId == null) {
            return cotext.getResourceAsStream("/WEB-INF/pages/login.html");
        } else {
            return cotext.getResourceAsStream("/WEB-INF/pages/news.html");
        }
    }

    /**
     *
     * @param begin set beginIndex from which news list begin
     *
     * @return NewsList List with 10 last Posts
     */

    @Path("news{beginFrom}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public NewsList getNextNews(@PathParam("beginFrom") int begin){
        NewsList newsList = new NewsList(begin);
               return newsList;
        }
}
