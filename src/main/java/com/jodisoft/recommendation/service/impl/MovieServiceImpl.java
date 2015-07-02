package com.jodisoft.recommendation.service.impl;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
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
     * default constructor
     *
     * @param repository the repository
     */
    @Autowired
    public MovieServiceImpl(final MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response find(final Long id) {
        final Movie movie = this.repository.findOne(id);
        if (movie == null) {
            return Response.status(HttpStatus.SC_NOT_FOUND)
                    .entity("No movie found with given id: " + id).build();
        }
        return Response.ok(movie).build();
    }
}