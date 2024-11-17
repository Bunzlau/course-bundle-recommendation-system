package com.google.coursebundlerecommendationsystem.service;

import com.google.coursebundlerecommendationsystem.model.PricingRate;
import com.google.coursebundlerecommendationsystem.model.Provider;
import com.google.coursebundlerecommendationsystem.model.QuoteResponseDto;
import com.google.coursebundlerecommendationsystem.model.Topic;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TwoTopicMatchStrategy implements QuoteCalculationStrategy {

    @Override
    public Optional<QuoteResponseDto> calculateQuote(Provider provider,
            Map<Topic, Integer> topTopics) {
        double totalQuote = provider.getTopics().stream()
                .filter(topTopics::containsKey)
                .mapToDouble(topTopics::get)
                .sum();

        return Optional.of(new QuoteResponseDto(provider.getProvider(),
                totalQuote * PricingRate.TWO_TOPICS.getRate()));
    }

    @Override
    public boolean isSupported(long matchingTopics) {
        return matchingTopics == 2;
    }
}
