package com.recommendation.rest.v1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

import com.recommendation.model.Message;
import com.recommendation.model.Movie;
import com.recommendation.model.User;

public class RecommendationResourceTest extends BaseJerseyTest {
    @Test
    public void testGetUser() {
        final Response response = target("users/1").request().get();

        assertNotNull(response);
        assertEquals(response.getStatus(), 200);

        final User u = response.readEntity(User.class);

        assertNotNull(u);
        assertNotNull(u.getEmail());
        assertNotNull(u.getFirstName());
        assertNotNull(u.getMoviePreferences());
        assertNotNull(u.toString());
        assertNotNull(u.hashCode());

        final Long id = 1L;
        assertEquals(u.getId(), id);
    }

    @Test
    public void testRecommend() {
        final Response response = target("users/1/recommendations").request()
                .get();

        assertNotNull(response);
        assertEquals(response.getStatus(), 200);

        final List<Movie> ents = response
                .readEntity(new GenericType<List<Movie>>() {
                });
        assertNotNull(ents);
        assertEquals(ents.size(), 2);

        for (final Movie m : ents) {
            assertNotNull(m.getId());
            assertNotNull(m.getGenre());
            assertNotNull(m.getImdb_id());
            assertNotNull(m.getName());
            assertNotNull(m.getSimilarMovies());

            assertEquals(m, m);
            assertNotEquals(m, null);
            assertNotEquals(m, new Movie());
            assertNotEquals(m, new User());
            assertNotNull(m.toString());
            assertNotNull(m.hashCode());
        }
    }
    
    @Test
    public void testRecommendException() {
        final Response response = target("users/6/recommendations").request()
                .get();

        assertNotNull(response);
        assertEquals(response.getStatus(), 400);
        
        final Message m = response.readEntity(Message.class);
        assertNotNull(m);
        assertEquals(m.getInfo(), "Unable to make recommendation for userId: 6");
        assertNotNull(m.hashCode());
    }

    @Test
    public void testGetUserNotFound() {
        final Response response = target("users/999982828/recommendations")
                .request().get();

        assertNotNull(response);
        // user doesn't exist
        assertEquals(response.getStatus(), 404);

        final Message m = response.readEntity(Message.class);
        assertNotNull(m);
        assertNotNull(m.toString());
        assertEquals(m.getInfo(), "No user found with id: "+999982828);
        assertNotNull(m.hashCode());

        final Message m2 = new Message(404, "blah");
        assertNotEquals(m, m2);
        assertNotEquals(m, new Movie());
        assertNotEquals(m, null);
    }

    @Test
    public void testGetWebappException() {
        final Response response = target("blah").request().get();

        assertNotNull(response);
        // not valid url
        assertEquals(response.getStatus(), 404);
    }
}