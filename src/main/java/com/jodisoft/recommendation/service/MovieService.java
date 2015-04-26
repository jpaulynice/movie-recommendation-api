package com.jodisoft.recommendation.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Jay Paulynice
 *
 */
@Path("movies")
@Produces({ MediaType.APPLICATION_JSON })
public interface MovieService {
    /**
     * Find a movie by id
     *
     * @param movieId the movie id
     * @return the movie details for the id
     */
    @GET
    @Path("{movieId}")
    public Response find(@PathParam("movieId") Long movieId);
}