package com.david.urlshortener.services.strategy;

/**
 * Strategy interface for generating short codes.
 * Different implementations can generate codes differently
 * (random, hash-based, base62, etc.)
 */
public interface ShortCodeStrategy {

    /**
     * Generates a short code for a given original URL.
     *
     * @param originalUrl the original long URL
     * @return generated short code
     */
    String generate(String originalUrl);
}
