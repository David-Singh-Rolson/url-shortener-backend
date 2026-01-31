package com.david.urlshortener.services.strategy;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomShortCodeStrategy implements ShortCodeStrategy {

    @Override
    public String generate(String originalUrl){
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 7);
    }
}
