package com.jodisoft.recommendation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jodisoft.recommendation.model.User;
import com.jodisoft.recommendation.repository.UserRepository;
import com.jodisoft.recommendation.service.UserService;

/**
 * @author Jay Paulynice
 *
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    /**
     * @param repository the repository
     */
    @Autowired
    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User find(final Integer id) {
        return repository.findOne(id);
    }
}