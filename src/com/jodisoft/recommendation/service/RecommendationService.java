package com.jodisoft.recommendation.service;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

/**
 * @author Jay Paulynice
 *
 */
public interface RecommendationService {
    /**
     * @return
     * @throws IOException
     * @throws TasteException
     */
    public List<RecommendedItem> recommend() throws IOException, TasteException;
}
