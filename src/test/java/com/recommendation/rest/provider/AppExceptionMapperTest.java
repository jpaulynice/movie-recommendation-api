package com.recommendation.rest.provider;

import static org.testng.Assert.assertNotNull;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.recommendation.config.SpringConfig;

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
    }
}
