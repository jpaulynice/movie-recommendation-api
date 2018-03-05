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
import com.recommendation.service.RecommendationService;
import com.recommendation.service.UserService;

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
    private final UserService userService;

    @Autowired
    public RecommendationResource(final RecommendationService service, final UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GET
    @Path("{userId}")
    public Response getUser(@PathParam("userId") final Long id) {
        return Response.ok(userService.getUser(id)).build();
    }

    @GET
    @Path("{userId}/recommendations")
    public Response recommend(@PathParam("userId") final Long userId, @QueryParam("limit") final int limit) {
        final List<Movie> vals = service.recommend(userService.getUser(userId), limit);
        final GenericEntity<List<Movie>> res = new GenericEntity<List<Movie>>(vals) {};

        return Response.ok(res).build();
    }
}
