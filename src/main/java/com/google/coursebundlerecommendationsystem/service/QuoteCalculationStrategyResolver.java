package com.google.coursebundlerecommendationsystem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class QuoteCalculationStrategyResolver {
    private final List<QuoteCalculationStrategy> strategies;

    public QuoteCalculationStrategyResolver(List<QuoteCalculationStrategy> strategies) {
        this.strategies = strategies;
    }

    public Optional<QuoteCalculationStrategy> resolveStrategy(long matchingTopics) {
        return strategies.stream()
                .filter(strategy -> strategy.isSupported(matchingTopics))
                .findFirst();
    }
}
