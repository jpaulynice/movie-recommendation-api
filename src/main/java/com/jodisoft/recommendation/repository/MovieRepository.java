package com.jodisoft.recommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jodisoft.recommendation.entities.Movie;

/**
 * @author Jay Paulynice
 *
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // nothing to see here
}
