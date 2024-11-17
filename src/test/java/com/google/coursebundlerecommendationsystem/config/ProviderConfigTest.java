package com.google.coursebundlerecommendationsystem.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.coursebundlerecommendationsystem.model.Provider;
import com.google.coursebundlerecommendationsystem.model.Topic;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;

public class ProviderConfigTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
    private final ProviderLoader providerLoader = new ProviderLoader(objectMapper, resourceLoader);


    @Test
    public void should_LoadProvidersFromTheFileAndTranslateItToProvider() {
        List<Provider> providers = providerLoader.getProviders();

        assertEquals(3, providers.size(), "Providers list should contain 3 items");

        Provider providerA = providers.getFirst();
        assertEquals("provider_a", providerA.provider());
        assertTrue(providerA.topics().contains(Topic.MATH));
        assertTrue(providerA.topics().contains(Topic.SCIENCE));
    }
}
