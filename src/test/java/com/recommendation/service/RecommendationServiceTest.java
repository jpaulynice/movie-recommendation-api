package com.recommendation.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.recommendation.config.SpringConfig;
import com.recommendation.exception.UserNotFoundException;
import com.recommendation.model.Movie;

@ContextConfiguration(classes = SpringConfig.class)
public class RecommendationServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private RecommendationService service;

    @Test
    public void testNotNull() {
        assertNotNull(service);
    }

    @Test
    public void testGetUser() {
        assertNotNull(service.getUser(1L));
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void testGetUserNotFound() {
        service.getUser(986322222L);
    }

    @Test
    public void testGetRecomm() {
        List<Movie> list = service.recommend(1L, 2);

        assertNotNull(list);
        assertEquals(list.size(), 2);

        for (int i = 0; i < list.size() - 1; i++) {
            Assert.assertTrue(list.get(i).getRank() < list.get(i + 1).getRank());
        }
        assertNotNull(service.recommend(1L, 0));
    }
}