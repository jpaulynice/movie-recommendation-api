package com.jodisoft.recommendation.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLBooleanPrefJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.AllSimilarItemsCandidateItemsStrategy;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.jdbc.MySQLJDBCInMemoryItemSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jodisoft.recommendation.entities.Movie;
import com.jodisoft.recommendation.repository.MovieRepository;
import com.jodisoft.recommendation.service.RecommendationService;
import com.jodisoft.recommendation.service.model.MovieRecommendation;
import com.jodisoft.recommendation.service.model.mapper.MovieMapper;

/**
 * Items similarity based recommendation engine with data stored in a MySQL
 * database.
 *
 * @author Jay Paulynice
 */
@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final DataSource dataSource;
    private final MovieRepository movieRepository;
    private ItemBasedRecommender recommender;
    private final MovieMapper mapper;

    /**
     * Default constructor with dataSource and movieService
     *
     * @param dataSource the dataSource to set
     * @param movieRepository the movie repository
     * @param mapper mapper for movie entity to dto etc.
     */
    @Autowired
    public RecommendationServiceImpl(final DataSource dataSource,
            final MovieRepository movieRepository, final MovieMapper mapper) {
        this.movieRepository = movieRepository;
        this.dataSource = dataSource;
        this.mapper = mapper;
        initRecommender();
    }

    @Override
    public Response recommend(final Long userId, final int howMany) {
        List<RecommendedItem> items = null;
        try {
            items = recommender.recommend(userId, howMany);
        } catch (final TasteException e) {
            logger.info("Exception occurred.", e);
        }
        final Set<Movie> movies = getRecommendedMovies(items);
        final MovieRecommendation res = new MovieRecommendation(
                mapper.toModelSet(movies));
        final GenericEntity<MovieRecommendation> entity = new GenericEntity<MovieRecommendation>(
                res) {
        };
        return Response.ok(entity).build();
    }

    /**
     * Initialize the recommender with a mysql datasource
     */
    private void initRecommender() {
        logger.debug("initializing mysql item similarity and preference data model.");
        final ItemSimilarity similarity = new MySQLJDBCInMemoryItemSimilarity(
                dataSource);
        final AllSimilarItemsCandidateItemsStrategy candidateStrategy = new AllSimilarItemsCandidateItemsStrategy(
                similarity);
        final DataModel dataModel = new MySQLBooleanPrefJDBCDataModel(
                dataSource);
        recommender = new GenericItemBasedRecommender(dataModel, similarity,
                candidateStrategy, candidateStrategy);
    }

    /**
     * For each recommended item, fetch the details from the database.
     *
     * @param items list of recommended items
     * @return list of movie with details
     */
    private Set<Movie> getRecommendedMovies(final List<RecommendedItem> items) {
        final Set<Movie> recommendedMovies = new HashSet<>();
        for (final RecommendedItem item : items) {
            final Movie movie = movieRepository.findOne(item.getItemID());
            recommendedMovies.add(movie);
        }

        return recommendedMovies;
    }
}