package com.jodisoft.recommendation.service.impl;

import java.util.ArrayList;
import java.util.List;

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

import com.jodisoft.recommendation.model.Movie;
import com.jodisoft.recommendation.service.MovieService;
import com.jodisoft.recommendation.service.RecommendationService;

/**
 * @author Jay Paulynice
 *
 */
@Service
public class MySQLRecommendationEngine implements RecommendationService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final DataSource dataSource;
    final MovieService mService;

    /**
     * @param dataSource the dataSource to set
     * @param mService movie service
     */
    @Autowired
    public MySQLRecommendationEngine(final DataSource dataSource,
            final MovieService mService) {
        this.dataSource = dataSource;
        this.mService = mService;
    }

    @Override
    public List<Movie> recommend(final Integer userId, final int howMany)
            throws TasteException {
        logger.info("initializing mysql preference model");
        final DataModel model = new MySQLBooleanPrefJDBCDataModel(dataSource);
        final ItemSimilarity similarity = new MySQLJDBCInMemoryItemSimilarity(
                dataSource);
        final AllSimilarItemsCandidateItemsStrategy candidateStrategy = new AllSimilarItemsCandidateItemsStrategy(
                similarity);
        final ItemBasedRecommender recommender = new GenericItemBasedRecommender(
                model, similarity, candidateStrategy, candidateStrategy);
        final List<RecommendedItem> items = recommender.recommend(userId,
                howMany);

        return getRecommendedMovies(items);
    }

    private List<Movie> getRecommendedMovies(final List<RecommendedItem> items) {
        final List<Movie> recommendedMovies = new ArrayList<>();

        for (final RecommendedItem item : items) {
            final Integer itemId = Integer.valueOf(String.valueOf(item
                    .getItemID()));
            final Movie movie = mService.find(itemId);
            if (movie != null) {
                recommendedMovies.add(movie);
            }
        }

        return recommendedMovies;
    }
}