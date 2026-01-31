package com.david.urlshortener.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlResponse {
    private String shortUrl;
    private String originalUrl;
}
