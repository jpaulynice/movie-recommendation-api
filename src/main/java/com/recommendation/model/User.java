package com.recommendation.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * User entity object
 *
 * @author Jay Paulynice
 *
 */
@Entity(name = "users")
public class User {
    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String firstName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @ManyToMany
    @JoinTable(name = "taste_preferences",
               joinColumns = { @JoinColumn(name = "user_id") },
               inverseJoinColumns = { @JoinColumn(name = "item_id") })
    private Set<Movie> moviePreferences = new HashSet<>();

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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the moviePreferences
     */
    public Set<Movie> getMoviePreferences() {
        return moviePreferences;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param moviePreferences the moviePreferences to set
     */
    public void setMoviePreferences(final Set<Movie> moviePreferences) {
        this.moviePreferences = moviePreferences;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", email="
                + email + "]";
    }
}
