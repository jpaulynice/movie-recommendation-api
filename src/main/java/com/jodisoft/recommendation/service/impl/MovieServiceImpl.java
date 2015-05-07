package com.jodisoft.recommendation.service.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jodisoft.recommendation.repository.MovieRepository;
import com.jodisoft.recommendation.service.MovieService;
import com.jodisoft.recommendation.service.model.mapper.MovieMapper;

/**
 * @author Jay Paulynice
 *
 */
@Service
@Transactional
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository repository;

    @Autowired
    private MovieMapper mapper;

    /**
     * default constructor
     */
    public MovieServiceImpl() {
        // nothing to see here
    }

    @Override
    public Response find(final Long id) {
        return Response.ok(mapper.toModel(repository.findOne(id))).build();

    }
}