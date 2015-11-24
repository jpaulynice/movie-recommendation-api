package com.jodisoft.recommendation.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Simple interface for user api
 *
 * @author Jay Paulynice
 */
@Path("users")
@Produces({ MediaType.APPLICATION_JSON })
public interface UserService {
    /**
     * Find a user by id
     *
     * @param userId the user id
     * @return the user details for the id
     */
    @GET
    @Path("{userId}")
    Response find(@PathParam("userId") Long userId);
}
