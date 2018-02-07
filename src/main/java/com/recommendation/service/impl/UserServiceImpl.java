package com.recommendation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.recommendation.exception.UserNotFoundException;
import com.recommendation.model.User;
import com.recommendation.repository.UserRepository;
import com.recommendation.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(final UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User getUser(final Long id) {
        final User u = userRepo.findOne(id);
        if (u == null) {
            throw new UserNotFoundException("No user found with id: " + id);
        }

        return u;
    }
}
