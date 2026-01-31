package com.david.urlshortener.services;

import com.david.urlshortener.dto.UrlResponse;
import com.david.urlshortener.exception.UrlNotFoundException;
import com.david.urlshortener.model.Url;
import com.david.urlshortener.repository.UrlRepository;
import com.david.urlshortener.response.ApiResponse;
import com.david.urlshortener.services.strategy.ShortCodeStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlServiceImpl implements UrlService {

    private static final String BASE_URL = "http://localhost:8080/";

    private final UrlRepository urlRepository;
    private final ShortCodeStrategy shortCodeStrategy;

    public UrlServiceImpl(UrlRepository urlRepository, ShortCodeStrategy shortCodeStrategy) {
        this.shortCodeStrategy = shortCodeStrategy;
        this.urlRepository = urlRepository;
    }

    @Override
    public ApiResponse<UrlResponse> createShortUrl(String originalUrl) {
        String shortCode = shortCodeStrategy.generate(originalUrl);
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortCode);
        url.setCreatedAt(LocalDateTime.now());
        urlRepository.save(url);

        UrlResponse response = new UrlResponse();
        response.setShortUrl(BASE_URL + shortCode);

        return ApiResponse.success("URL shortened successfully", response);
    }

    @Override
    public ApiResponse<UrlResponse> getOriginalUrl(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("URL not found for short code: " + shortCode));

        UrlResponse response = new UrlResponse();
        response.setOriginalUrl(url.getOriginalUrl());

        return ApiResponse.success("Original URL retrieved", response);
    }
}
