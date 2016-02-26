package com.recommendation.rest.v1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Set;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

import com.recommendation.model.Movie;

public class RecommendationResourceTest extends BaseJerseyTest {
    @Test
    public void testGet() {
        final Response response = target("users/1/recommendations").request()
                .get();

        assertNotNull(response);
        assertEquals(response.getStatus(), 200);

        final Set<Movie> ents = response
                .readEntity(new GenericType<Set<Movie>>() {
                });
        assertNotNull(ents);
        assertEquals(ents.size(), 2);
    }

    @Test
    public void testGetUserNotFound() {
        final Response response = target("users/999982828282/recommendations")
                .request().get();

        assertNotNull(response);
        // user doesn't exist
        assertEquals(response.getStatus(), 404);
    }

    @Test
    public void testGetWebappException() {
        final Response response = target("blah").request().get();

        assertNotNull(response);
        // not valid url
        assertEquals(response.getStatus(), 404);
    }
}