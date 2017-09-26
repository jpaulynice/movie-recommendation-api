package com.recommendation.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;
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
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration for Spring Data/JPA using annotations. This assumes the
 * datasource is configured as a jndi resource as in tomcat's server.xml with
 * name {@code jndiDBresource}
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.recommendation.repository")
public class SpringDataConfig {
    @Autowired
    private Environment env;

    @Bean
    EntityManagerFactory entityManagerFactory() throws NamingException,
            PropertyVetoException {
        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(false);

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setJpaProperties(hibernateProperties());
        factory.setPackagesToScan("com.recommendation.model");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }
    
    @Bean
    public ItemBasedRecommender recommender() throws PropertyVetoException{
    	final ItemSimilarity similarity = new MySQLJDBCInMemoryItemSimilarity(dataSource());
        final AllSimilarItemsCandidateItemsStrategy candidateStrategy = new AllSimilarItemsCandidateItemsStrategy(
                similarity);
        ItemBasedRecommender recommender;
		try {
			DataModel dataModel = new ReloadFromJDBCDataModel(new MySQLJDBCDataModel(dataSource()));
	        recommender = new GenericItemBasedRecommender(dataModel, similarity,candidateStrategy, candidateStrategy);
		} catch (TasteException e) {
			throw new RuntimeException("Unable to create the recommender. An exception occurred", e);
		}
		
		return recommender;
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        final DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("db.driver"));
        ds.setUrl(env.getProperty("db.url"));

        DatabasePopulatorUtils.execute(dbPopulator(), ds);

        return new ConnectionPoolDataSource(ds);
    }

    private DatabasePopulator dbPopulator() {
        final ResourceDatabasePopulator dp = new ResourceDatabasePopulator();
        dp.addScript(new ClassPathResource("META-INF/data/sql/ddl.sql"));
        dp.addScript(new ClassPathResource("META-INF/data/sql/init.sql"));

        return dp;
    }

    @Bean
    Properties hibernateProperties() {
        final Properties props = new Properties();
        props.setProperty("hibernate.dialect",
                "org.hibernate.dialect.MySQL5Dialect");
        props.setProperty("hibernate.dbcp.initialSize", "5");
        props.setProperty("hibernate.dbcp.maxActive", "20");
        props.setProperty("hibernate.dbcp.maxIdle", "20");
        props.setProperty("hibernate.dbcp.minIdle", "0");

        return props;
    }

    @Bean
    PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

    /**
     * @return persistence exception translator
     */
    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
