package com.recommendation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.recommendation.exception.RecommendationException;
import com.recommendation.model.Movie;
import com.recommendation.model.User;
import com.recommendation.repository.MovieRepository;
import com.recommendation.service.RecommendationService;

/**
 * Default implementation for {@link RecommendationService}
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
@Service
@Transactional
public class RecommendationServiceImpl implements RecommendationService {
	private static final int DEFAULT_LIMIT = 10;

	private ItemBasedRecommender recommender;
	private final MovieRepository repo;

	@Autowired
	public RecommendationServiceImpl(final ItemBasedRecommender recommender, final MovieRepository repo) {
		this.repo = repo;
		this.recommender = recommender;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Movie> recommend(final User user, int howMany) {
		if (howMany <= 0) {
			howMany = DEFAULT_LIMIT;
		}

		return getRecommendedMovies(getItems(user.getId(), howMany));
	}

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
			throw new RecommendationException("Unable to make recommendation for userId: " + userId);
		}

		return items;
	}
}
