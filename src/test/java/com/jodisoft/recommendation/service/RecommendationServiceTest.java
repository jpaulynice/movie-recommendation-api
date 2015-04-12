package com.jodisoft.recommendation.service;

import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author Jay Paulynice
 *
 */
@ContextConfiguration(locations = "/META-INF/spring/spring.xml")
public class RecommendationServiceTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    @Qualifier("mySQLRecommendationEngine")
    private RecommendationService service;

    /**
     * @throws TasteException if errors
     */
    @Test
    public void testNotNull() throws TasteException {
        Assert.assertNotNull(service);
    }

    /**
     * @throws TasteException if errors
     */
    @Test
    public void testMySqlRecommendationService() throws TasteException {
        Assert.assertNotNull(service.recommend(2, 3));
    }
}