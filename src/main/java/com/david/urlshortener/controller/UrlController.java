package com.david.urlshortener.controller;

import com.david.urlshortener.dto.UrlRequest;
import com.david.urlshortener.dto.UrlResponse;
import com.david.urlshortener.response.ApiResponse;
import com.david.urlshortener.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class UrlController {
    private final UrlService urlService;

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ApiResponse<UrlResponse>> shortenUrl(@Validated(UrlRequest.ShortenUrl.class) @RequestBody UrlRequest request) {
        ApiResponse<UrlResponse> response = urlService.createShortUrl(request.getOriginalUrl());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/get-original-url")
    public ResponseEntity<ApiResponse<UrlResponse>> getOriginalUrlFromShortUrl(@Validated(UrlRequest.GetOriginalUrl.class) @RequestBody UrlRequest request) {
        ApiResponse<UrlResponse> response = urlService.getOriginalUrl(request.getShortUrl());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fetch-all-urls")
    public ResponseEntity<ApiResponse<UrlResponse>> getAllUrls() {
        return null;
    }
}
