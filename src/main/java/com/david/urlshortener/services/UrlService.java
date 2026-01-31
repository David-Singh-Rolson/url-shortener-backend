package com.david.urlshortener.services;

import com.david.urlshortener.dto.UrlResponse;
import com.david.urlshortener.model.Url;
import com.david.urlshortener.response.ApiResponse;
public interface UrlService {
    ApiResponse<UrlResponse> createShortUrl(String originalUrl);
    ApiResponse<UrlResponse>  getOriginalUrl(String shortCode);
}
