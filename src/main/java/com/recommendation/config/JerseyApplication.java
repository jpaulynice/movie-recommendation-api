package com.recommendation.config;

import org.glassfish.jersey.server.ResourceConfig;

import com.recommendation.rest.provider.AppExceptionMapper;
import com.recommendation.rest.v1.RecommendationResource;

/**
 * Jersey application configuration for our recommendation api
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
public class JerseyApplication extends ResourceConfig {
    public JerseyApplication() {
        register(RecommendationResource.class);
        register(AppExceptionMapper.class);
    }
}
