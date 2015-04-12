package com.jodisoft.recommendation.service;

import com.jodisoft.recommendation.model.Movie;

/**
 * @author Jay Paulynice
 *
 */
public interface MovieService {
    /**
     * @param id the movie id
     * @return the movie details for the id
     */
    public Movie find(final Integer id);
}
