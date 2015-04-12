package com.jodisoft.recommendation.engine;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jodisoft.recommendation.model.Movie;
import com.jodisoft.recommendation.service.MovieService;

/**
 * User similarity based recommendation engine with data stored in a csv file.
 *
 * @author Jay Paulynice
 */
@Service
public class CsvRecommendationEngine implements RecommendationEngine {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private DataModel dataModel;
    final MovieService mService;

    /**
     * create new csv recommendation engine
     *
     * @param mService movie service
     */
    @Autowired
    public CsvRecommendationEngine(final MovieService mService) {
        this.mService = mService;
    }

    /**
     * @param csvFilePath the path to the data in csv format
     * @param mService movie service
     *
     */
    public CsvRecommendationEngine(final String csvFilePath,
            final MovieService mService) {
        init(csvFilePath);
        this.mService = mService;
    }

    @Override
    public Set<Movie> recommend(final Integer userId, final int howMany)
            throws TasteException {
        final UserSimilarity similarity = new PearsonCorrelationSimilarity(
                dataModel);
        final UserNeighborhood neighborhood = new ThresholdUserNeighborhood(
                0.1, similarity, dataModel);
        final UserBasedRecommender recommender = new GenericUserBasedRecommender(
                dataModel, neighborhood, similarity);
        final List<RecommendedItem> items = recommender.recommend(userId,
                howMany);

        return getRecommendedMovies(items);
    }

    /**
     * Initialize the data model given the csv file path.
     *
     * @param csvFilePath the path for the csv file
     */
    private void init(final String csvFilePath) {
        try {
            dataModel = new FileDataModel(new File(csvFilePath));
        } catch (final IOException e) {
            logger.error("Exception initializing data model", e);
        }
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