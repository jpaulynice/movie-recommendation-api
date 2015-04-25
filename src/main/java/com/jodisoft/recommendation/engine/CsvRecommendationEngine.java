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
    private MovieService mService;
    private UserBasedRecommender recommender;
    private static final Double DEFAULT_SIMILARITY_THRESHOLD = 0.1;

    /**
     * create new csv recommendation engine with no parameters
     */
    public CsvRecommendationEngine() {
        // nothing to see here
    }

    /**
     * create new csv recommendation engine with default similarity threshold
     *
     * @param csvFilePath the path to the data in csv format
     * @param mService movie service
     */
    public CsvRecommendationEngine(final String csvFilePath,
            final MovieService mService) {
        this.mService = mService;
        initRecommender(csvFilePath, DEFAULT_SIMILARITY_THRESHOLD);
    }

    /**
     * create new csv recommendation engine with given similarity threshold
     *
     * @param csvFilePath the path to the data in csv format
     * @param mService movie service
     * @param similarityThreshold the minimum similarity to consider this needs
     *        to be between -1.0 to 1.0 where 1.0 represents perfect similarity.
     */
    public CsvRecommendationEngine(final String csvFilePath,
            final MovieService mService, final Double similarityThreshold) {
        this.mService = mService;
        initRecommender(csvFilePath, similarityThreshold);
    }

    @Override
    public Set<Movie> recommend(final Long userId, final int howMany)
            throws TasteException {
        final List<RecommendedItem> items = recommender.recommend(userId,
                howMany);

        return getRecommendedMovies(items);
    }

    /**
     * Initialize the recommender
     *
     * @param csvFilePath the path to the csv file
     * @param similarityThreashold
     */
    private void initRecommender(final String csvFilePath,
            final Double similarityThreashold) {
        try {
            final DataModel dataModel = new FileDataModel(new File(csvFilePath));
            final UserSimilarity similarity = new PearsonCorrelationSimilarity(
                    dataModel);
            final UserNeighborhood neighborhood = new ThresholdUserNeighborhood(
                    similarityThreashold, similarity, dataModel);
            recommender = new GenericUserBasedRecommender(dataModel,
                    neighborhood, similarity);
        } catch (final TasteException | IOException e) {
            logger.error("Exception while initializing the recommender.", e);
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
            recommendedMovies.add(getMovie(item.getItemID()));
        }

        return recommendedMovies;
    }

    /**
     * Fetch the details of the recommended item
     *
     * @param item the recommended item
     * @return movie details
     */
    private Movie getMovie(final Long itemId) {
        return mService.find(itemId);
    }
}