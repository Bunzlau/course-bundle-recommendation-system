package com.google.coursebundlerecommendationsystem.model;

import java.util.Collections;
import java.util.Set;

public class Provider {
    private final String provider;
    private final Set<Topic> topics;


    public Provider(String provider, Set<Topic> topics) {
        this.provider = provider;
        this.topics = topics;
    }

    public Set<Topic> getTopics() {
        return Collections.unmodifiableSet(topics);
    }

    public String getProvider() {
        return provider;
    }
}
