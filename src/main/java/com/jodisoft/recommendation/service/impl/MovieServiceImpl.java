package com.jodisoft.recommendation.service.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public MovieServiceImpl() {
    }

    @Override
    public Response find(final Long id) {
        return Response.ok(repository.findOne(id)).build();
    }
}