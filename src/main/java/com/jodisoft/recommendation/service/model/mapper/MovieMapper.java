package com.jodisoft.recommendation.service.model.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.jodisoft.recommendation.entities.Movie;

@Component
public class MovieMapper {
    public Movie toEntity(
            final com.jodisoft.recommendation.service.model.Movie model) {
        final Movie entity = new Movie();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setImdb_id(model.getImdb_id());
        entity.setGenre(model.getGenre());
        return entity;
    }

    public Set<Movie> toEntitySet(
            final Set<com.jodisoft.recommendation.service.model.Movie> models) {
        final Set<Movie> entities = new HashSet<>();
        for (final com.jodisoft.recommendation.service.model.Movie m : models) {
            entities.add(toEntity(m));
        }
        return entities;
    }

    public com.jodisoft.recommendation.service.model.Movie toModel(
            final Movie entity) {
        final com.jodisoft.recommendation.service.model.Movie model = new com.jodisoft.recommendation.service.model.Movie();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setImdb_id(entity.getImdb_id());
        model.setGenre(entity.getGenre());

        return model;
    }

    public Set<com.jodisoft.recommendation.service.model.Movie> toModelSet(
            final Set<Movie> entities) {
        final Set<com.jodisoft.recommendation.service.model.Movie> models = new HashSet<>();
        for (final Movie m : entities) {
            models.add(toModel(m));
        }
        return models;
    }
}
