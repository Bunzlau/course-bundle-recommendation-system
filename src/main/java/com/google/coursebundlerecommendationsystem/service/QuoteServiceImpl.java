package com.google.coursebundlerecommendationsystem.service;

import com.google.coursebundlerecommendationsystem.config.ProviderLoader;
import com.google.coursebundlerecommendationsystem.model.Provider;
import com.google.coursebundlerecommendationsystem.model.QuoteResponseDto;
import com.google.coursebundlerecommendationsystem.model.TeacherRequestDto;
import com.google.coursebundlerecommendationsystem.model.Topic;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

    public static final int LIMITED_TOPIC = 3;
    private final List<Provider> providers;
    private final QuoteCalculationStrategyResolver strategyResolver;


    public QuoteServiceImpl(ProviderLoader providerLoader,
            QuoteCalculationStrategyResolver strategyResolver) {
        this.providers = providerLoader.getProviders();
        this.strategyResolver = strategyResolver;
    }

    @Override
    public List<QuoteResponseDto> calculateQuote(TeacherRequestDto teacherRequest) {
        Map<Topic, Integer> topTopics = getTop3Topics(teacherRequest.resources());

        return providers.stream()
                .map(provider -> calculateQuoteForProvider(provider, topTopics))
                .flatMap(Optional::stream)
                .toList();
    }

    private Optional<QuoteResponseDto> calculateQuoteForProvider(Provider provider,
            Map<Topic, Integer> topTopics) {
        long matchingTopics = provider.getTopics().stream()
                .filter(topTopics::containsKey)
                .count();

        return strategyResolver.resolveStrategy(matchingTopics)
                .flatMap(strategy -> strategy.calculateQuote(provider, topTopics));
    }

    private Map<Topic, Integer> getTop3Topics(Map<Topic, Integer> resources) {
        return resources.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(LIMITED_TOPIC)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
