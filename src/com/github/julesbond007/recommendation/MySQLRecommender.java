package com.github.julesbond007.recommendation;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLBooleanPrefJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.AllSimilarItemsCandidateItemsStrategy;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.jdbc.MySQLJDBCInMemoryItemSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.util.List;

public class MySQLRecommender {

    public static void main(String[] args) {
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUrl("jdbc:mysql://localhost/recommendationdb");
            dataSource.setPort(3306);
            dataSource.setUser("root");
            dataSource.setPassword("jodisoft");

            DataModel model = new MySQLBooleanPrefJDBCDataModel(dataSource);

            ItemSimilarity similarity = new MySQLJDBCInMemoryItemSimilarity(dataSource);
            AllSimilarItemsCandidateItemsStrategy candidateStrategy =
                    new AllSimilarItemsCandidateItemsStrategy(similarity);
            ItemBasedRecommender recommender = new GenericItemBasedRecommender(model,
                    similarity, candidateStrategy, candidateStrategy);

            List<RecommendedItem> recommendations =
                    (List<RecommendedItem>) recommender.recommend(2, 3);
            for (RecommendedItem recommendation : recommendations) {
                System.out.println(recommendation);
            }

        } catch (TasteException e) {
            e.printStackTrace();
        }

    }
}
