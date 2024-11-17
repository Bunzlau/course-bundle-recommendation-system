package com.google.coursebundlerecommendationsystem.service;

import com.google.coursebundlerecommendationsystem.model.QuoteResponseDto;
import com.google.coursebundlerecommendationsystem.model.TeacherRequestDto;
import java.util.List;

public interface QuoteService {

    List<QuoteResponseDto> calculateQuote(TeacherRequestDto request);
}
