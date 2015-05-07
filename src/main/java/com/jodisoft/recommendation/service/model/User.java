package com.jodisoft.recommendation.service.model;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.jodisoft.recommendation.entities.Movie;

/**
 * DTO User object
 *
 * @author Jay Paulynice
 *
 */
@XmlRootElement
public class User {
    private Long id;
    private String firstName;
    private String email;
    private Set<Movie> moviePreferences;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the moviePreferences
     */
    public Set<Movie> getMoviePreferences() {
        return moviePreferences;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @param moviePreferences the moviePreferences to set
     */
    public void setMoviePreferences(final Set<Movie> moviePreferences) {
        this.moviePreferences = moviePreferences;
    }
}
