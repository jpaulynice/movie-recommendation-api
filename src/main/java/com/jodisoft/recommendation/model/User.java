package com.jodisoft.recommendation.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User entity object
 *
 * @author Jay Paulynice
 *
 */
@XmlRootElement
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "taste_preferences", joinColumns = { @JoinColumn(
            name = "user_id") }, inverseJoinColumns = { @JoinColumn(
                    name = "item_id") })
    private Set<Movie> moviePreferences;

    /**
     * create new user object
     */
    public User() {
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @return the moviePreferences
     */
    public Set<Movie> getMoviePreferences() {
        return this.moviePreferences;
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
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [id=" + this.id + ", firstName=" + this.firstName
                + ", email=" + this.email + "]";
    }
}
