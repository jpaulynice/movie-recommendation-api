package com.jodisoft.recommendation.service.impl;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jodisoft.recommendation.model.User;
import com.jodisoft.recommendation.repository.UserRepository;
import com.jodisoft.recommendation.service.UserService;

/**
 * @author Jay Paulynice
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    /**
     * default constructor
     * 
     * @param repository the repository
     */
    @Autowired
    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response find(final Long id) {
        final User user = this.repository.findOne(id);
        if (user == null) {
            return Response.status(HttpStatus.SC_NOT_FOUND)
                    .entity("No user found with given id: " + id).build();
        }
        return Response.ok(user).build();
    }
}