package com.google.coursebundlerecommendationsystem.service;

import com.google.coursebundlerecommendationsystem.model.PricingRate;
import com.google.coursebundlerecommendationsystem.model.Provider;
import com.google.coursebundlerecommendationsystem.model.QuoteResponseDto;
import com.google.coursebundlerecommendationsystem.model.Topic;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class SingleTopicMatchStrategy implements QuoteCalculationStrategy {

    @Override
    public Optional<QuoteResponseDto> calculateQuote(Provider provider,
            Map<Topic, Integer> topTopics) {
        List<Entry<Topic, Integer>> sortedTopics = topTopics.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .toList();

        for (int i = 0; i < sortedTopics.size(); i++) {
            if (provider.topics().contains(sortedTopics.get(i).getKey())) {
                PricingRate priorityRate = PricingRate.fromPriority(i);
                return Optional.of(new QuoteResponseDto(
                        provider.provider(),
                        sortedTopics.get(i).getValue() * priorityRate.getRate()
                ));
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean isSupported(long matchingTopics) {
        return matchingTopics == 1;
    }


}
