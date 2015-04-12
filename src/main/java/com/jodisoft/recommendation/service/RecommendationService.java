package com.jodisoft.recommendation.service;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;

import com.jodisoft.recommendation.model.Movie;

/**
 * @author Jay Paulynice
 *
 */
public interface RecommendationService {
    /**
     * @param userId the user to recommend items for
     * @param howMany the number of recommendations to make
     * @return list of recommended movies
     * @throws TasteException if errors
     */
    public List<Movie> recommend(final Integer userId, final int howMany)
            throws TasteException;
}
