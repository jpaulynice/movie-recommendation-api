package com.jodisoft.recommendation.service;

import com.jodisoft.recommendation.model.User;

/**
 * @author Jay Paulynice
 *
 */
public interface UserService {
    /**
     * @param id the user id
     * @return the user details for the id
     */
    public User find(Integer id);
}
