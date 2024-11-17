package com.google.coursebundlerecommendationsystem.model;

import java.util.Collections;
import java.util.Set;

public record Provider(String provider, Set<Topic> topics) {

    @Override
    public Set<Topic> topics() {
        return Collections.unmodifiableSet(topics);
    }
}
