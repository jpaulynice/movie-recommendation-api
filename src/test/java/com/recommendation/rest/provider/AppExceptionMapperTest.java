package com.recommendation.rest.provider;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.recommendation.config.SpringConfig;
import com.recommendation.exception.UserNotFoundException;

@ContextConfiguration(classes = SpringConfig.class)
public class AppExceptionMapperTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private AppExceptionMapper mapper;

    @Test
    public void testNotNull() {
        assertNotNull(mapper);
    }

    @Test
    public void test500() {
        final Response res = mapper.toResponse(new RuntimeException());
        assertNotNull(res);
        assertEquals(res.getStatus(), 500);
    }

    @Test
    public void test404() {
        final Response res = mapper.toResponse(new UserNotFoundException(
                "no user found with id: 9899"));
        assertNotNull(res);
        assertEquals(res.getStatus(), 404);
    }

    @Test
    public void testWebappException() {
        final Response res = mapper.toResponse(new WebApplicationException());
        assertNotNull(res);
        assertEquals(res.getStatus(), 500);
    }
}