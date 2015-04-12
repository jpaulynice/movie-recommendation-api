package com.jodisoft.recommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jodisoft.recommendation.model.User;

/**
 * @author Jay Paulynice
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    // nothing to see here
}
