package com.recommendation.repository;

import org.springframework.data.repository.CrudRepository;

import com.recommendation.model.Movie;

/**
 * Movie repository
 *
 * @author Jay Paulynice
 */
public interface MovieRepository extends CrudRepository<Movie, Long> {
    // nothing to see here
}
