package com.recommendation.service;

import java.util.List;

import com.recommendation.model.Movie;
import com.recommendation.model.User;

/**
 * Items similarity based recommendation engine with data stored in a MySQL
 * database.
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
public interface RecommendationService {
    List<Movie> recommend(final User user, int howMany);
}
