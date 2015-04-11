package com.jodisoft.recommendation.model;

import java.sql.Date;
import java.util.List;

import com.jodisoft.recommendation.model.enums.MovieGenre;

/**
 * @author Jay Paulynice
 *
 */
public class Movie {
    private int id;
    private String name;
    private MovieGenre genre;
    private List<Actor> actors;
    private Date releaseDate;

    /**
     * @return the id
     */
    public int getId() {
        return id;
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
     * @return the actors
     */
    public List<Actor> getActors() {
        return actors;
    }

    /**
     * @return the releaseDate
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param id the id to set
     */
    public void setId(final int id) {
        this.id = id;
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
     * @param actors the actors to set
     */
    public void setActors(final List<Actor> actors) {
        this.actors = actors;
    }

    /**
     * @param releaseDate the releaseDate to set
     */
    public void setReleaseDate(final Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
