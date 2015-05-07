package com.jodisoft.recommendation.service.model.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.jodisoft.recommendation.entities.Movie;

/**
 * @author Jay Paulynice
 *
 */
@Component
public class MovieMapper {

    /**
     * @param entity the entity to map
     * @return dto object
     */
    public com.jodisoft.recommendation.service.model.Movie toModel(
            final Movie entity) {
        final com.jodisoft.recommendation.service.model.Movie model = new com.jodisoft.recommendation.service.model.Movie();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setImdb_id(entity.getImdb_id());
        model.setGenre(entity.getGenre());

        return model;
    }

    /**
     * @param entities list of entities
     * @return list of dto's
     */
    public Set<com.jodisoft.recommendation.service.model.Movie> toModelSet(
            final Set<Movie> entities) {
        final Set<com.jodisoft.recommendation.service.model.Movie> models = new HashSet<>();
        for (final Movie m : entities) {
            models.add(toModel(m));
        }
        return models;
    }
}
