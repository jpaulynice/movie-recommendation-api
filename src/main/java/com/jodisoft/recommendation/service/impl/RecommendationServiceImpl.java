package com.jodisoft.recommendation.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
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

import com.jodisoft.recommendation.model.Movie;
import com.jodisoft.recommendation.repository.MovieRepository;
import com.jodisoft.recommendation.repository.UserRepository;
import com.jodisoft.recommendation.service.RecommendationService;

/**
 * Items similarity based recommendation engine with data stored in a MySQL
 * database.
 *
 * @author Jay Paulynice
 */
@Service
public class RecommendationServiceImpl implements RecommendationService {
    private static final int DEFAULT_LIMIT = 10;

    private static final Logger logger = LoggerFactory
            .getLogger(RecommendationServiceImpl.class);

    private final DataSource dataSource;
    private ItemBasedRecommender recommender;
    private final MovieRepository repo;
    private final UserRepository userRepo;

    /**
     * Default constructor with dataSource and movieService
     *
     * @param dataSource the dataSource to set
     * @param repo the movie repository
     * @param userRepo the user repository
     */
    @Autowired
    public RecommendationServiceImpl(final DataSource dataSource,
            final MovieRepository repo, final UserRepository userRepo) {
        this.repo = repo;
        this.dataSource = dataSource;
        this.userRepo = userRepo;
        initRecommender();
    }

    private List<RecommendedItem> getItems(Long userId, int howMany) {
        List<RecommendedItem> items = null;
        try {
            items = this.recommender.recommend(userId, howMany);
        } catch (final TasteException e) {
            logger.info("Exception occurred.", e);
        }

        return items;
    }

    /**
     * For each recommended item, fetch the details from the database.
     *
     * @param items list of recommended items
     * @return list of movie with details
     */
    private Set<Movie> getRecommendedMovies(final List<RecommendedItem> items) {
        final Set<Movie> movies = new HashSet<>();
        for (final RecommendedItem item : items) {
            final Movie movie = this.repo.findOne(item.getItemID());
            movies.add(movie);
        }

        return movies;
    }

    /**
     * Initialize the recommender with a mysql datasource
     */
    private void initRecommender() {
        logger.debug("initializing mysql item similarity and preference data model.");
        final ItemSimilarity similarity = new MySQLJDBCInMemoryItemSimilarity(
                this.dataSource);
        final AllSimilarItemsCandidateItemsStrategy candidateStrategy = new AllSimilarItemsCandidateItemsStrategy(
                similarity);
        final DataModel dataModel = new MySQLBooleanPrefJDBCDataModel(
                this.dataSource);
        this.recommender = new GenericItemBasedRecommender(dataModel,
                similarity, candidateStrategy, candidateStrategy);
    }

    private boolean inValidUser(Long userId) {
        return this.userRepo.findOne(userId) == null;
    }

    @Override
    public Response recommend(final Long userId, int howMany) {
        if (inValidUser(userId)) {
            return Response.status(HttpStatus.SC_NOT_FOUND)
                    .entity("No user found with id: " + userId).build();
        }

        if (howMany <= 0) {
            howMany = DEFAULT_LIMIT;
        }

        final Set<Movie> movies = getRecommendedMovies(getItems(userId, howMany));
        final GenericEntity<Set<Movie>> entities = new GenericEntity<Set<Movie>>(
                movies) {
        };

        return Response.ok(entities).build();
    }
}