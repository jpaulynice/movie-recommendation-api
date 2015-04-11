package com.jodisoft.recommendation.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

import com.jodisoft.recommendation.service.RecommendationService;

/**
 * @author Jay Paulynice
 *
 */
@Service
public class CsvRecommendationEngine implements RecommendationService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private DataModel dataModel;

    /**
     *
     */
    public CsvRecommendationEngine() {
        init();
    }

    private void init() {
        try {
            dataModel = new FileDataModel(new File(
                    "/META-INF/data/csv/dataset.csv"));
        } catch (final IOException e) {
            logger.error("Exception initializing data model", e);
        }
    }

    @Override
    public List<RecommendedItem> recommend() throws TasteException {
        final UserSimilarity similarity = new PearsonCorrelationSimilarity(
                dataModel);
        final UserNeighborhood neighborhood = new ThresholdUserNeighborhood(
                0.1, similarity, dataModel);
        final UserBasedRecommender recommender = new GenericUserBasedRecommender(
                dataModel, neighborhood, similarity);

        return recommender.recommend(2, 3);
    }
}
