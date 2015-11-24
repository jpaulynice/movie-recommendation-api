package com.jodisoft.recommendation.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Simple interface for the recommendation engine.
 *
 * @author Jay Paulynice
 */
@Path("/users/{userId}/recommendations")
@Produces({ MediaType.APPLICATION_JSON })
public interface RecommendationService {
    /**
     * Recommend a number of movies for a user.
     *
     * @param userId the user to recommend items for
     * @param howMany the number of recommendations to make
     * @return set of recommended movies
     */
    @GET
    Response recommend(@PathParam("userId") Long userId,
            @QueryParam("limit") int howMany);
}
