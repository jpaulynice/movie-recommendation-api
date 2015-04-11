package com.jodisoft.recommendation.service.impl;

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

import com.jodisoft.recommendation.service.RecommendationService;

/**
 * @author Jay Paulynice
 *
 */
@Service
public class MySQLRecommendationEngine implements RecommendationService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final DataSource dataSource;

    /**
     * @param dataSource the dataSource to set
     */
    @Autowired
    public MySQLRecommendationEngine(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<RecommendedItem> recommend() throws TasteException {
        final DataModel model = new MySQLBooleanPrefJDBCDataModel(dataSource);
        final ItemSimilarity similarity = new MySQLJDBCInMemoryItemSimilarity(
                dataSource);
        final AllSimilarItemsCandidateItemsStrategy candidateStrategy = new AllSimilarItemsCandidateItemsStrategy(
                similarity);
        final ItemBasedRecommender recommender = new GenericItemBasedRecommender(
                model, similarity, candidateStrategy, candidateStrategy);

        final List<RecommendedItem> movies = recommender.recommend(2, 3);
        logger.info("Movie recommendations for user:" + movies);

        return movies;
    }
}
