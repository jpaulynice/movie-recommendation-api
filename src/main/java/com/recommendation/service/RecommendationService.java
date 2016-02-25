package com.recommendation.service;

import java.util.Set;

import com.recommendation.model.Movie;

public interface RecommendationService {
    Set<Movie> recommend(final Long userId, final int limit);
}