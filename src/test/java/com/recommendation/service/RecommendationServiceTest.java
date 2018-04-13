package com.recommendation.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.recommendation.exception.RecommendationException;
import com.recommendation.model.Movie;
import com.recommendation.model.User;

@ContextConfiguration(classes = SpringConfigTest.class)
public class RecommendationServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private RecommendationService service;

    @Test
    public void testNotNull() {
        assertNotNull(service);
    }

    @Test
    public void testGetRecomm() {
        User u = new User();
        u.setId(1L);

        List<Movie> list = service.recommend(u, 2);

        assertNotNull(list);
        assertEquals(list.size(), 2);

        for (int i = 0; i < list.size() - 1; i++) {
            Assert.assertTrue(list.get(i).getRank() < list.get(i + 1).getRank());
        }
        assertNotNull(service.recommend(u, 0));
    }
    
    @Test(expectedExceptions = RecommendationException.class)
    public void testGetRecException() {
        User u = new User();
        u.setId(9999L);

        service.recommend(u, 2);
    }
}