package com.recommendation.rest.v1;

import java.util.Set;

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

/**
 * Items similarity based recommendation engine with data stored in a MySQL
 * database.
 *
 * @author Jay Paulynice
 */
@Component
@Path("users/{userId}/recommendations")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class RecommendationResource {
    private final RecommendationService service;

    @Autowired
    public RecommendationResource(final RecommendationService service) {
        this.service = service;
    }

    @GET
    public Response recommend(@PathParam("userId") final Long userId,
            @QueryParam("limit") final int limit) {
        final Set<Movie> vals = service.recommend(userId, limit);
        final GenericEntity<Set<Movie>> res = new GenericEntity<Set<Movie>>(
                vals) {
        };

        return Response.ok(res).build();
    }
}
