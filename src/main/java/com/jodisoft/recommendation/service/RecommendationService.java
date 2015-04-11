package com.jodisoft.recommendation.service;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

/**
 * @author Jay Paulynice
 *
 */
public interface RecommendationService {
    /**
     * @return list of recommended items
     * @throws TasteException if errors
     */
    public List<RecommendedItem> recommend() throws TasteException;
}
