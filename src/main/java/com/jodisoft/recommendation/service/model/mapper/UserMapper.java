package com.jodisoft.recommendation.service.model.mapper;

import org.springframework.stereotype.Component;

import com.jodisoft.recommendation.service.model.User;

/**
 * @author Jay Paulynice
 *
 */
@Component
public class UserMapper {
    /**
     * @param user the user entity to map
     * @return the user dto
     */
    public User toUserDto(final com.jodisoft.recommendation.entities.User user) {
        if (user == null) {
            return null;
        }
        final User u = new User();
        u.setId(user.getId());
        u.setFirstName(user.getFirstName());
        u.setEmail(user.getEmail());

        return u;
    }
}
