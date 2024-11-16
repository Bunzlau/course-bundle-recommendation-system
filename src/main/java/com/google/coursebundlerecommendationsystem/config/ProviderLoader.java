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
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class ProviderLoader {
    final static String PROVIDER_LOCATION = "classpath:providers.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final String SPLIT_THE_TOPICS_BY_PLUS = "\\+";

    public List<Provider> providers() {
        try {
            File file = ResourceUtils.getFile(PROVIDER_LOCATION);

            ProvidersWrapper wrapper = mapper.readValue(file, ProvidersWrapper.class);

            return wrapper.getProviders().stream()
                    .map(raw -> new Provider(
                            raw.getName(),
                            Set.of(raw.getTopics().split(SPLIT_THE_TOPICS_BY_PLUS)).stream()
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
