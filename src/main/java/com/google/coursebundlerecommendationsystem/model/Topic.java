package com.google.coursebundlerecommendationsystem.model;

public enum Topic {
    MATH("math"),
    SCIENCE("science"),
    READING("reading"),
    HISTORY("history"),
    ART("art");

    private final String stringValue;

    Topic(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public static Topic fromString(String value) {
        for (Topic topic : values()) {
            if (topic.stringValue.equalsIgnoreCase(value)) {
                return topic;
            }
        }
        throw new IllegalArgumentException("Unknown topic: " + value);
    }
}
