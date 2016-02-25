package com.recommendation.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

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
    ComboPooledDataSource dataSource() throws PropertyVetoException {
        final ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("db.driver"));
        dataSource.setJdbcUrl(env.getProperty("db.url"));

        DatabasePopulatorUtils.execute(dbPopulator(), dataSource);

        return dataSource;
    }

    private DatabasePopulator dbPopulator() {
        final ResourceDatabasePopulator dbPopulator = new ResourceDatabasePopulator();
        dbPopulator
                .addScript(new ClassPathResource("META-INF/data/sql/ddl.sql"));
        dbPopulator.addScript(new ClassPathResource(
                "META-INF/data/sql/init.sql"));

        return dbPopulator;
    }

    @Bean
    Properties hibernateProperties() {
        final Properties props = new Properties();
        props.setProperty("hibernate.dialect",
                "org.hibernate.dialect.MySQL5Dialect");
        props.setProperty("hibernate.c3p0.min_size", "5");
        props.setProperty("hibernate.c3p0.max_size", "20");
        props.setProperty("hibernate.c3p0.timeout", "1800");
        props.setProperty("hibernate.c3p0.max_statements", "50");

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
