package com.recommendation.service;

import java.util.Set;

import com.recommendation.model.Movie;

/**
 * Items similarity based recommendation engine with data stored in a MySQL
 * database.
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
public interface RecommendationService {
    Set<Movie> recommend(final Long userId, final int limit);
}