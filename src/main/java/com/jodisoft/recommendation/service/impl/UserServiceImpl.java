package com.jodisoft.recommendation.service.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jodisoft.recommendation.repository.UserRepository;
import com.jodisoft.recommendation.service.UserService;

/**
 * @author Jay Paulynice
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    public UserServiceImpl() {
    }

    @Override
    public Response find(final Long id) {
        return Response.ok(repository.findOne(id)).build();
    }
}