package com.google.coursebundlerecommendationsystem.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

public record TeacherRequestDto(
        @NotNull(message = "Resources must not be null")
        @NotEmpty(message = "Resources must not be empty")
        Map<Topic, Integer> resources
) {

}