package com.recommendation.service;

import java.util.SortedSet;

import com.recommendation.model.Movie;
import com.recommendation.model.User;

/**
 * Items similarity based recommendation engine with data stored in a MySQL
 * database.
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
public interface RecommendationService {
	SortedSet<Movie> recommend(final Long userId, final int limit);

    User getUser(final Long id);
}