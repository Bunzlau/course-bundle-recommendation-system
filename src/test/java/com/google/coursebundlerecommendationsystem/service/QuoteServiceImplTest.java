package com.google.coursebundlerecommendationsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.coursebundlerecommendationsystem.config.ProviderLoader;
import com.google.coursebundlerecommendationsystem.model.QuoteResponseDto;
import com.google.coursebundlerecommendationsystem.model.TeacherRequestDto;
import com.google.coursebundlerecommendationsystem.model.Topic;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;

class QuoteServiceImplTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
    private final ProviderLoader providerLoader = new ProviderLoader(objectMapper, resourceLoader);
    private final QuoteCalculationStrategyResolver strategyResolver = new QuoteCalculationStrategyResolver(
            List.of(new TwoTopicMatchStrategy(), new SingleTopicMatchStrategy())
    );
    private final QuoteService quoteService = new QuoteServiceImpl(providerLoader,
            strategyResolver);


    @Test
    void should_GenerateQuotesForValidRequest() {
        TeacherRequestDto request = new TeacherRequestDto(Map.of(
                Topic.MATH, 50,
                Topic.SCIENCE, 30,
                Topic.READING, 20,
                Topic.HISTORY, 15,
                Topic.ART, 10
        ));

        List<QuoteResponseDto> quotes = quoteService.calculateQuote(request);

        assertEquals(3, quotes.size());
        assertTrue(quotes.stream()
                .anyMatch(q -> q.providerName().equals("provider_a") && q.quote() == 8.0));
        assertTrue(quotes.stream()
                .anyMatch(q -> q.providerName().equals("provider_b") && q.quote() == 5.0));
        assertTrue(quotes.stream()
                .anyMatch(q -> q.providerName().equals("provider_c") && q.quote() == 10.0));
    }

    @Test
    void should_ReturnEmptyListForNoMatchingProviders() {
        TeacherRequestDto request = new TeacherRequestDto(Map.of(
                Topic.ART, 20
        ));

        List<QuoteResponseDto> quotes = quoteService.calculateQuote(request);

        assertTrue(quotes.isEmpty());
    }

    @Test
    void should_ReturnEmptyListForEmptyResources() {
        TeacherRequestDto request = new TeacherRequestDto(Map.of());

        List<QuoteResponseDto> quotes = quoteService.calculateQuote(request);

        assertTrue(quotes.isEmpty(), "Expected no quotes for empty resources");
    }

}