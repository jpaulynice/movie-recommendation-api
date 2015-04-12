package com.jodisoft.recommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jodisoft.recommendation.model.Movie;

/**
 * @author Jay Paulynice
 *
 */
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    // nothing to see here
}
