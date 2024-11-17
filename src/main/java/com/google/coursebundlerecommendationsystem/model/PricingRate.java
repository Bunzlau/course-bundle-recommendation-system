package com.google.coursebundlerecommendationsystem.model;

public enum PricingRate {
    TWO_TOPICS(0.1),
    HIGHEST_PRIORITY(0.2),
    SECOND_PRIORITY(0.25),
    THIRD_PRIORITY(0.3);

    private final double rate;

    PricingRate(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public static PricingRate fromPriority(int priority) {
        return switch (priority) {
            case 0 -> HIGHEST_PRIORITY;
            case 1 -> SECOND_PRIORITY;
            case 2 -> THIRD_PRIORITY;
            default -> throw new IllegalArgumentException("Invalid priority index: " + priority);
        };
    }
}
