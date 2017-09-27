package com.recommendation.rest.v1;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.recommendation.model.Movie;
import com.recommendation.model.User;
import com.recommendation.service.RecommendationService;

/**
 * Jersey resource endpoint for our recommendation engine.
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
@Component
@Path("users")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class RecommendationResource {
    private final RecommendationService service;

    @Autowired
    public RecommendationResource(final RecommendationService service) {
        this.service = service;
    }

    @GET
    @Path("{userId}")
    public Response getUser(@PathParam("userId") final Long id) {
        final User u = service.getUser(id);

        return Response.ok(u).build();
    }

    @GET
    @Path("{userId}/recommendations")
    public Response recommend(@PathParam("userId") final Long userId, 
                              @QueryParam("limit") final int limit) {
        final List<Movie> vals = service.recommend(userId, limit);
        final GenericEntity<List<Movie>> res = new GenericEntity<List<Movie>>(vals) {};

        return Response.ok(res).build();
    }
}
