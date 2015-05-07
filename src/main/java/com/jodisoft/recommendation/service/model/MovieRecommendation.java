package com.jodisoft.recommendation.service.model;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jay Paulynice
 *
 */
@XmlRootElement
public class MovieRecommendation {
    @XmlElement
    private Set<Movie> movies;

    /**
     * default constructor
     */
    public MovieRecommendation() {

    }

    /**
     * @param movies the recommended movies
     */
    public MovieRecommendation(final Set<Movie> movies) {
        this.movies = movies;
    }
}
