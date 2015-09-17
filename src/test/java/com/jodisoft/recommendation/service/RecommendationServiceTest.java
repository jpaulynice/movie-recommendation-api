package com.jodisoft.recommendation.service;

import javax.ws.rs.core.Response;

import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Test recommendation service class
 *
 * @author Jay Paulynice
 *
 */
@ContextConfiguration(locations = "/applicationContext.xml")
public class RecommendationServiceTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private RecommendationService service;

    /**
     * Test service wired correctly
     *
     * @throws TasteException if errors
     */
    @Test
    public void testApiNotNull() throws TasteException {
        Assert.assertNotNull(this.service);
    }

    /**
     * @throws TasteException if errors
     */
    @Test
    public void testRecommendationService() throws TasteException {
        final Response res = this.service.recommend(2L, 3);

        Assert.assertNotNull(res);
        Assert.assertEquals(res.getStatus(), 200);

        Assert.assertNotNull(res.getEntity());
    }
}