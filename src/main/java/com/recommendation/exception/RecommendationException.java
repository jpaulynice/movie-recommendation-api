package com.recommendation.exception;

public class RecommendationException extends AbstractBaseException {
    private static final long serialVersionUID = -3340712431244069981L;

    public RecommendationException(final String message) {
        super(message);
    }

    @Override
    public int getStatus() {
        return 400;
    }
}
