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
     * @param userId the user to recommend items for
     * @param howMany the number of recommendations to make
     * @return list of recommended items
     * @throws TasteException if errors
     */
    public List<RecommendedItem> recommend(final int userId, final int howMany)
            throws TasteException;
}
