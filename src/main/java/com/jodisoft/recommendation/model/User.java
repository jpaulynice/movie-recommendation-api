package com.jodisoft.recommendation.model;

/**
 * @author Jay Paulynice
 *
 */
public class User extends BaseUser {
    private String email;

    /**
     * create new user object
     */
    public User() {
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }
}
