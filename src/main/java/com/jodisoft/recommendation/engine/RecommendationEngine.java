package com.jodisoft.recommendation.engine;

import java.util.Set;

import org.apache.mahout.cf.taste.common.TasteException;

import com.jodisoft.recommendation.model.Movie;

/**
 * Simple interface for the recommendation engine.
 *
 * @author Jay Paulynice
 */
public interface RecommendationEngine {
    /**
     * Recommend a number of movies for a user.
     *
     * @param userId the user to recommend items for
     * @param howMany the number of recommendations to make
     * @return set of recommended movies
     * @throws TasteException if errors
     */
    public Set<Movie> recommend(final Integer userId, final int howMany)
            throws TasteException;
}
