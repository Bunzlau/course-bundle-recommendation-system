package com.google.coursebundlerecommendationsystem.config;

import static org.junit.jupiter.api.Assertions.*;


import com.google.coursebundlerecommendationsystem.model.Provider;
import com.google.coursebundlerecommendationsystem.model.Topic;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProviderConfigTest {

    @Autowired
    List<Provider> providers;


    @Test
    public void should_LoadProvidersFromTheFileAndTranslateItToProvider(){
         final int expectedProviderSize = 3;

        assertNotNull(providers, "Providers list can not be null");
        assertEquals(expectedProviderSize, providers.size());
        Provider providerA = providers.getFirst();

        assertEquals("provider_a", providerA.getProvider());
        assertTrue(providerA.getTopics().contains(Topic.MATH), "Provider_a should offer MATH");
        assertTrue(providerA.getTopics().contains(Topic.SCIENCE), "Provider_a should offer SCIENCE");
    }


}
