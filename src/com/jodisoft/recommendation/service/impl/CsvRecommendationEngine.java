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

import com.jodisoft.recommendation.service.RecommendationService;

public class CsvRecommendationEngine implements RecommendationService {

    @Override
    public List<RecommendedItem> recommend() throws IOException, TasteException {

        final DataModel model = new FileDataModel(new File("/dataset.csv"));
        final UserSimilarity similarity = new PearsonCorrelationSimilarity(
                model);
        final UserNeighborhood neighborhood = new ThresholdUserNeighborhood(
                0.1, similarity, model);

        final UserBasedRecommender recommender = new GenericUserBasedRecommender(
                model, neighborhood, similarity);

        return recommender.recommend(2, 3);
    }

}
