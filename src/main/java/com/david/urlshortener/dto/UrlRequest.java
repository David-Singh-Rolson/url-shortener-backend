package com.david.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UrlRequest {

    @NotBlank(message = "Original URL must not be blank", groups = ShortenUrl.class)
    @Pattern(
            regexp = "https?://.*",
            message = "URL must start with http:// or https://",
            groups = ShortenUrl.class
    )
    @JsonProperty("original_url")
    private String originalUrl;

    @NotBlank(message = "Short URL must not be blank", groups = GetOriginalUrl.class)
    @JsonProperty("short_url")
    private String shortUrl;

    public interface ShortenUrl {}
    public interface GetOriginalUrl {}
}
