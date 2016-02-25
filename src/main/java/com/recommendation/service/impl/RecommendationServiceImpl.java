package com.recommendation.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.recommendation.exception.UserNotFoundException;
import com.recommendation.model.Movie;
import com.recommendation.repository.MovieRepository;
import com.recommendation.repository.UserRepository;
import com.recommendation.service.RecommendationService;

/**
 * Default implementation for {@link RecommendationService}
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
@Service
@Transactional
public class RecommendationServiceImpl implements RecommendationService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final int DEFAULT_LIMIT = 10;

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

    public boolean inValidUser(final Long userId) {
        return userRepo.findOne(userId) == null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
                   readOnly = true)
    public Set<Movie> recommend(final Long userId, int howMany) {
        if (inValidUser(userId)) {
            throw new UserNotFoundException("No user found with id: " + userId);
        }

        if (howMany <= 0) {
            howMany = DEFAULT_LIMIT;
        }

        return getRecommendedMovies(getItems(userId, howMany));
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
            final Movie movie = repo.findOne(item.getItemID());
            movies.add(movie);
        }

        return movies;
    }

    private List<RecommendedItem> getItems(final Long userId, final int howMany) {
        List<RecommendedItem> items = null;
        try {
            items = recommender.recommend(userId, howMany);
        } catch (final TasteException e) {
            logger.info("Exception occurred.", e);
        }

        return items;
    }
}