package com.recommendation.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.AllSimilarItemsCandidateItemsStrategy;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.jdbc.MySQLJDBCInMemoryItemSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring configuration using annotation replacing 'applicationContext.xml'
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
@Configuration
@ComponentScan(basePackages = { "com.recommendation" },
               excludeFilters = @Filter(type = FilterType.REGEX, pattern = "com.recommendation.config.*"))
@Import({ SpringDataConfig.class })
@PropertySource("classpath:META-INF/properties/db.properties")
public class SpringConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public ItemBasedRecommender recommender() throws PropertyVetoException, TasteException {
        return createRecommender(new ReloadFromJDBCDataModel(new MySQLJDBCDataModel(dataSource)), new MySQLJDBCInMemoryItemSimilarity(dataSource));
    }

    private ItemBasedRecommender createRecommender(final DataModel dataModel,final ItemSimilarity similarity) {
        final AllSimilarItemsCandidateItemsStrategy strategy = new AllSimilarItemsCandidateItemsStrategy(similarity);

        return new GenericItemBasedRecommender(dataModel, similarity, strategy, strategy);
    }
}
