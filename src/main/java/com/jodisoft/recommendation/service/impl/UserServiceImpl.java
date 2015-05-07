package com.jodisoft.recommendation.service.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jodisoft.recommendation.repository.UserRepository;
import com.jodisoft.recommendation.service.UserService;
import com.jodisoft.recommendation.service.model.mapper.UserMapper;

/**
 * @author Jay Paulynice
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    /**
     * default constructor
     */
    public UserServiceImpl() {
        // nothing to see here
    }

    @Override
    public Response find(final Long id) {
        return Response.ok(mapper.toUserDto(repository.findOne(id))).build();
    }
}