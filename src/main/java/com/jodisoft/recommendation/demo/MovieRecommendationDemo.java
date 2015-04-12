package com.jodisoft.recommendation.demo;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jodisoft.recommendation.service.RecommendationService;

/**
 * @author Jay Paulynice
 *
 */
public class MovieRecommendationDemo {

    /**
     * @param args the arguments
     * @throws TasteException if errors
     */
    public static void main(final String[] args) throws TasteException {
        final Logger logger = LoggerFactory
                .getLogger(MovieRecommendationDemo.class);
        final ApplicationContext context = new ClassPathXmlApplicationContext(
                "/META-INF/spring/spring.xml");

        final RecommendationService service = (RecommendationService) context
                .getBean("mySQLRecommendationEngine");

        // get 3 recommendations for user id 2
        final List<RecommendedItem> movies = service.recommend(2, 3);
        logger.info("movie recommendations: " + movies);
    }
}