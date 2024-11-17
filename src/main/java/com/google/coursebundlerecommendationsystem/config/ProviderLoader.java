package com.google.coursebundlerecommendationsystem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.coursebundlerecommendationsystem.model.Provider;
import com.google.coursebundlerecommendationsystem.model.Topic;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class ProviderLoader {

    private static final String PROVIDER_LOCATION = "classpath:providers.json";
    private static final String TOPIC_DELIMITER = "\\+";

    private final ObjectMapper objectMapper;
    private final ResourceLoader resourceLoader;

    public ProviderLoader(ObjectMapper objectMapper, ResourceLoader resourceLoader) {
        this.objectMapper = objectMapper;
        this.resourceLoader = resourceLoader;
    }

    public List<Provider> getProviders() {
        Resource resource = resourceLoader.getResource(PROVIDER_LOCATION);

        try {
            ProvidersWrapper providersWrapper = objectMapper.readValue(resource.getInputStream(), ProvidersWrapper.class);

            return providersWrapper.getProviders().stream()
                    .map(providerMapper -> new Provider(
                            providerMapper.getName(),
                            parseTopics(providerMapper.getTopics())))
                    .toList();

        } catch (IOException e) {
            throw new IllegalStateException("Failed to load providers configuration", e);
        }
    }

    private Set<Topic> parseTopics(String topics) {
        return Set.of(topics.split(TOPIC_DELIMITER)).stream()
                .map(Topic::fromString)
                .collect(Collectors.toSet());
    }


    private static class ProvidersWrapper {
        private List<ProviderMapper> providers;

        public List<ProviderMapper> getProviders() {
            return providers;
        }
    }

    private static class ProviderMapper {
        private String name;
        private String topics;

        public String getName() {
            return name;
        }

        public String getTopics() {
            return topics;
        }

    }
}