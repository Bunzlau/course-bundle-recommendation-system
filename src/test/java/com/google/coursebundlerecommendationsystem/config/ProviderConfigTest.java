package com.google.coursebundlerecommendationsystem.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.coursebundlerecommendationsystem.model.Provider;
import com.google.coursebundlerecommendationsystem.model.Topic;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProviderConfigTest {

    ProviderLoader providerLoader;

    @BeforeEach
    void setup() {
         providerLoader = new ProviderLoader();
    }

    @Test
    public void should_LoadProvidersFromTheFileAndTranslateItToProvider() {
        // Act
        List<Provider> providers = providerLoader.providers();

        // Assert
        assertNotNull(providers, "Providers list should not be null");
        assertEquals(3, providers.size(), "Providers list should contain 3 items");

        Provider providerA = providers.getFirst();
        assertEquals("provider_a", providerA.getProvider());
        assertTrue(providerA.getTopics().contains(Topic.MATH));
        assertTrue(providerA.getTopics().contains(Topic.SCIENCE));
    }
}
