package com.recommendation.repository;

import org.springframework.data.repository.CrudRepository;

import com.recommendation.model.Movie;

/**
 * Movie repository
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
public interface MovieRepository extends CrudRepository<Movie, Long> {}
