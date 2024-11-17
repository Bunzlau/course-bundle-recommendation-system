package com.google.coursebundlerecommendationsystem.service;

import com.google.coursebundlerecommendationsystem.model.Provider;
import com.google.coursebundlerecommendationsystem.model.QuoteResponseDto;
import com.google.coursebundlerecommendationsystem.model.Topic;
import java.util.Map;
import java.util.Optional;

public interface QuoteCalculationStrategy {
    Optional<QuoteResponseDto> calculateQuote(Provider provider, Map<Topic, Integer> resources);
    boolean isSupported(long matchingTopics);

}
