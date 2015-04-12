package com.jodisoft.recommendation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jodisoft.recommendation.model.Movie;
import com.jodisoft.recommendation.repository.MovieRepository;
import com.jodisoft.recommendation.service.MovieService;

/**
 * @author Jay Paulynice
 *
 */
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository repository;

    /**
     * @param repository the repository
     */
    @Autowired
    public MovieServiceImpl(final MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public Movie find(final Integer id) {
        return repository.findOne(id);
    }
}