package com.jodisoft.recommendation.service.model;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.jodisoft.recommendation.entities.MovieGenre;

@XmlRootElement
public class Movie {
    private Long id;
    private String imdb_id;
    private String name;
    private MovieGenre genre;
    private Set<Movie> similarMovies;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the imdb_id
     */
    public String getImdb_id() {
        return imdb_id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the genre
     */
    public MovieGenre getGenre() {
        return genre;
    }

    /**
     * @return the similarMovies
     */
    public Set<Movie> getSimilarMovies() {
        return similarMovies;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param imdb_id the imdb_id to set
     */
    public void setImdb_id(final String imdb_id) {
        this.imdb_id = imdb_id;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(final MovieGenre genre) {
        this.genre = genre;
    }

    /**
     * @param similarMovies the similarMovies to set
     */
    public void setSimilarMovies(final Set<Movie> similarMovies) {
        this.similarMovies = similarMovies;
    }
}
