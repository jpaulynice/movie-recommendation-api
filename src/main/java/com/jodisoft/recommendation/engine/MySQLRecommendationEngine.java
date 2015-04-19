package com.jodisoft.recommendation.engine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLBooleanPrefJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.AllSimilarItemsCandidateItemsStrategy;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.jdbc.MySQLJDBCInMemoryItemSimilarity;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jodisoft.recommendation.model.Movie;
import com.jodisoft.recommendation.service.MovieService;

/**
 * Items similarity based recommendation engine with data stored in a MySQL
 * database.
 *
 * @author Jay Paulynice
 */
@Service
public class MySQLRecommendationEngine implements RecommendationEngine {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final DataSource dataSource;
    final MovieService mService;
    private ItemBasedRecommender recommender;

    /**
     * @param dataSource the dataSource to set
     * @param mService movie service
     */
    @Autowired
    public MySQLRecommendationEngine(final DataSource dataSource,
            final MovieService mService) {
        this.mService = mService;
        this.dataSource = dataSource;
        initRecommender();
    }

    @Override
    public Set<Movie> recommend(final Integer userId, final int howMany)
            throws TasteException {
        return getRecommendedMovies(recommender.recommend(userId, howMany));
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
        recommender = new GenericItemBasedRecommender(
                new MySQLBooleanPrefJDBCDataModel(dataSource), similarity,
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
            recommendedMovies.add(getMovie(item));
        }

        return recommendedMovies;
    }

    /**
     * Fetch the details of the recommended item
     *
     * @param item the recommended item
     * @return movie details
     */
    public Movie getMovie(final RecommendedItem item) {
        final Integer itemId = Integer
                .valueOf(String.valueOf(item.getItemID()));
        return mService.find(itemId);
    }
}