package com.jodisoft.recommendation.service.impl;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jodisoft.recommendation.model.Movie;
import com.jodisoft.recommendation.repository.MovieRepository;
import com.jodisoft.recommendation.service.MovieService;

/**
 * @author Jay Paulynice
 *
 */
@Service
@Transactional
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository repository;

    /**
     * default constructor
     */
    public MovieServiceImpl() {
        // nothing to see here
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