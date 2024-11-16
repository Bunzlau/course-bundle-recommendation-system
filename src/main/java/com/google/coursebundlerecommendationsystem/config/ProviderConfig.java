package com.google.coursebundlerecommendationsystem.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.coursebundlerecommendationsystem.model.Provider;
import com.google.coursebundlerecommendationsystem.model.Topic;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

@Configuration
public class ProviderConfig {
    final static String PROVIDER_LOCATION = "classpath:providers.json";
    ObjectMapper mapper = new ObjectMapper();

    @Bean
    public List<Provider> providers() {
        try {
            File file = ResourceUtils.getFile(PROVIDER_LOCATION);

            ObjectMapper mapper = new ObjectMapper();
            ProvidersWrapper wrapper = mapper.readValue(file, ProvidersWrapper.class);

            return wrapper.getProviders().stream()
                    .map(raw -> new Provider(
                            raw.getName(),
                            Set.of(raw.getTopics().split("\\+")).stream()
                                    .map(Topic::fromString)
                                    .collect(Collectors.toSet())))
                    .toList();

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to load providers configuration", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static class ProvidersWrapper {
        private List<ProviderMapper> providers;

        public List<ProviderMapper> getProviders() {
            return providers;
        }

        public void setProviders(List<ProviderMapper> providers) {
            this.providers = providers;
        }
    }

    private static class ProviderMapper {

        private String name;
        private String topics;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTopics() {
            return topics;
        }

        public void setTopics(String topics) {
            this.topics = topics;
        }
    }

}
