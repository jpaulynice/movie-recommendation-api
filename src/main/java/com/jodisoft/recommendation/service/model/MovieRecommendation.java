package com.jodisoft.recommendation.service.model;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MovieRecommendation {
    @XmlElement
    private Set<Movie> movies;

    public MovieRecommendation() {

    }

    public MovieRecommendation(final Set<Movie> movies) {
        this.movies = movies;
    }
}
