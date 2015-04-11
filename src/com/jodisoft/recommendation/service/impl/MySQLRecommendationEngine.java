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

import com.jodisoft.recommendation.service.RecommendationService;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * @author Jay Paulynice
 *
 */
public class MySQLRecommendationEngine implements RecommendationService {

    @Override
    public List<RecommendedItem> recommend() throws TasteException {

        final DataSource dataSource = getDatasource();
        final DataModel model = new MySQLBooleanPrefJDBCDataModel(dataSource);
        final ItemSimilarity similarity = new MySQLJDBCInMemoryItemSimilarity(
                dataSource);

        final AllSimilarItemsCandidateItemsStrategy candidateStrategy = new AllSimilarItemsCandidateItemsStrategy(
                similarity);

        final ItemBasedRecommender recommender = new GenericItemBasedRecommender(
                model, similarity, candidateStrategy, candidateStrategy);

        return recommender.recommend(2, 3);
    }

    private static DataSource getDatasource() {
        final MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost/recommendationdb");
        dataSource.setPort(3306);
        dataSource.setUser("root");
        dataSource.setPassword("jodisoft");

        return dataSource;
    }
}
