package com.google.coursebundlerecommendationsystem.controler;

import com.google.coursebundlerecommendationsystem.model.QuoteResponseDto;
import com.google.coursebundlerecommendationsystem.model.TeacherRequestDto;
import com.google.coursebundlerecommendationsystem.service.QuoteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quote")
public class QuoteController {
    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public ResponseEntity<?> generateQuotes(@Valid @RequestBody TeacherRequestDto request) {
        List<QuoteResponseDto> quotes = quoteService.calculateQuote(request);

        if (quotes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No quotes could be generated for the provided data");
        }

        return ResponseEntity.ok(quotes);
    }
}
