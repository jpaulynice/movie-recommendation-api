package com.recommendation.service;

import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.recommendation.exception.UserNotFoundException;

@ContextConfiguration(classes = SpringConfigTest.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private UserService service;

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
}
