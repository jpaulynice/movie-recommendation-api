package com.jodisoft.recommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jodisoft.recommendation.entities.User;

/**
 * @author Jay Paulynice
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    // nothing to see here
}
