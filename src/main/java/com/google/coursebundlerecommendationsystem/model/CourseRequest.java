package com.google.coursebundlerecommendationsystem.model;

public class CourseRequest {

    private final String topic;
    private final int resource;

    public CourseRequest(String topic, int resource) {
        this.topic = topic;
        this.resource = resource;
    }

    public String getTopic() {
        return topic;
    }

    public int getResource() {
        return resource;
    }
}
