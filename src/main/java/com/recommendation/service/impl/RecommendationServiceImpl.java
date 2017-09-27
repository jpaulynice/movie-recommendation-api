package com.recommendation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.recommendation.exception.UserNotFoundException;
import com.recommendation.model.Movie;
import com.recommendation.model.User;
import com.recommendation.repository.MovieRepository;
import com.recommendation.repository.UserRepository;
import com.recommendation.service.RecommendationService;

/**
 * Default implementation for {@link RecommendationService}
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
@Service
@Transactional
public class RecommendationServiceImpl implements RecommendationService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final int DEFAULT_LIMIT = 10;

    private ItemBasedRecommender recommender;
    private final MovieRepository repo;
    private final UserRepository userRepo;

    /**
     * Default constructor with dataSource and movieService
     *
     * @param dataSource the dataSource to set
     * @param repo the movie repository
     * @param userRepo the user repository
     */
    @Autowired
    public RecommendationServiceImpl(final ItemBasedRecommender recommender, final MovieRepository repo, final UserRepository userRepo) {
        this.repo = repo;
        this.recommender = recommender;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Movie> recommend(final Long userId, int howMany) {
        final User user = getUser(userId);

        if (howMany <= 0) {
            howMany = DEFAULT_LIMIT;
        }

        return getRecommendedMovies(getItems(user.getId(), howMany));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User getUser(final Long id) {
        final User u = userRepo.findOne(id);
        if (u == null) {
            throw new UserNotFoundException("No user found with id: " + id);
        }

        return u;
    }

    /**
     * For each recommended item, fetch the details from the database.
     *
     * @param items list of recommended items
     * @return list of movie with details
     */
    private List<Movie> getRecommendedMovies(final List<RecommendedItem> items) {
        final List<Movie> movies = new ArrayList<>();
        int count = 0;
        for (final RecommendedItem item : items) {
            final Movie movie = repo.findOne(item.getItemID());
            movie.setRank(++count);
            movies.add(movie);
        }

        return movies;
    }

    private List<RecommendedItem> getItems(final Long userId, final int howMany) {
        List<RecommendedItem> items = null;
        try {
            items = recommender.recommend(userId, howMany);
        } catch (final TasteException e) {
            logger.error("Exception occurred.", e);
        }

        return items;
    }
}
