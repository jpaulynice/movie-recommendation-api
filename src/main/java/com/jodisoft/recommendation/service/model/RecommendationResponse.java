package com.jodisoft.recommendation.service.model;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.jodisoft.recommendation.entities.Movie;

@XmlRootElement
public class RecommendationResponse {
    @XmlElement
    private Set<Movie> movieRecommendations;

    public RecommendationResponse() {

    }

    public RecommendationResponse(final Set<Movie> movieRecommendations) {
        this.movieRecommendations = movieRecommendations;
    }
}
