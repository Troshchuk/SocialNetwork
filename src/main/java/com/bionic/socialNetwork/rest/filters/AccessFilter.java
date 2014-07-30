package com.bionic.socialNetwork.rest.filters;


import com.bionic.socialNetwork.logic.SessionLogic;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by Bish_UA on 22.07.2014.
 */

@Provider
public class AccessFilter implements ContainerRequestFilter {
    private static final Logger LOGGER = LogManager.getLogger();

    @Context
    private HttpServletRequest webRequest;
    @Context
    HttpHeaders httpHeaders;


    @Override
    public ContainerRequest filter(ContainerRequest request) {
        String logging = webRequest.getRemoteAddr() +
                         " " + webRequest.getMethod() +
                         " " + webRequest.getRequestURI();

        Map cookies = httpHeaders.getCookies();
        Cookie cookie = (Cookie) cookies.get("sessionId");
        SessionLogic sessionLogic = new SessionLogic();
        Long userId = -1l;

        if (cookie != null) {
            userId = sessionLogic.verifySession(cookie.getValue());
            logging += " sessionId: " + cookie.getValue() + " userId: " + userId;
        }
        LOGGER.info(logging);

        if (userId == -1) {
            try {
                URI uri = request.getRequestUri();
                if (!request.getRequestUri().equals(new URI(
                        "http://" + uri.getHost() + ":" + uri.getPort() +
                        "/sn/index/login")) &&
                    !request.getRequestUri().equals(new URI(
                            "http://" + uri.getHost() + ":" + uri.getPort() +
                            "/sn/index/registration"))) {
                    request.setUris(request.getBaseUri(),
                                    new URI("http://" + uri.getHost() + ":" +
                                            uri.getPort() + "/sn/index"));
                }

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        } else {
            webRequest.setAttribute("userId", userId);
        }


        return request;


    }

}