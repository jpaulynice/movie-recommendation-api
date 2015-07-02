package com.jodisoft.recommendation.service;

import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Test service class
 *
 * @author Jay Paulynice
 *
 */
@ContextConfiguration(locations = "/applicationContext.xml")
public class RecommendationServiceTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private RecommendationService service;

    /**
     * @throws TasteException if errors
     */
    @Test
    public void testMySqlRecommendationService() throws TasteException {
        Assert.assertNotNull(this.service.recommend(2L, 3));
    }

    /**
     * @throws TasteException if errors
     */
    @Test
    public void testNotNull() throws TasteException {
        Assert.assertNotNull(this.service);
    }
}