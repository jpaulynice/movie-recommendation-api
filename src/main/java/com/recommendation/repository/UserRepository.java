package com.recommendation.repository;

import org.springframework.data.repository.CrudRepository;

import com.recommendation.model.User;

/**
 * User repository
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
public interface UserRepository extends CrudRepository<User, Long> {}

