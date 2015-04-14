package com.jodisoft.recommendation.demo;

import java.util.Set;

import org.apache.mahout.cf.taste.common.TasteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jodisoft.recommendation.engine.RecommendationEngine;
import com.jodisoft.recommendation.model.Movie;
import com.jodisoft.recommendation.model.User;
import com.jodisoft.recommendation.service.UserService;

/**
 * Demo for the movie recommendation engine based on MySQL data store
 *
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

        final RecommendationEngine service = (RecommendationEngine) context
                .getBean("mySQLRecommendationEngine");

        final UserService uService = (UserService) context
                .getBean("userServiceImpl");
        final Integer userId = 1;
        final int howMany = 3;
        final User user = uService.find(userId);

        // get 3 movie recommendations for user id
        final Set<Movie> movies = service.recommend(userId, howMany);
        logger.info("recommended movies for " + user.getFirstName() + ": "
                + movies);

        // for each movie get similar movies
        for (final Movie m : movies) {
            logger.info("Similar movies to " + m.getName() + ": "
                    + m.getSimilarMovies());
        }

        // get user's movie preferences
        logger.info(user.getFirstName() + " preferences: "
                + user.getMoviePreferences());
    }
}