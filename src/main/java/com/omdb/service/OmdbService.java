package com.omdb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class OmdbService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${omdb.apikey}")
    private String apiKey;

    @Value("${omdb.base-url}")
    private String baseUrl;

    @Cacheable(value = "search", key = "#title + '_' + #type + '_' + #page")
    public Map searchByTitle(String title, String type, int page) {

        String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("apikey", apiKey)
                .queryParam("s", encodedTitle)
                .queryParam("type", type)
                .queryParam("page", page)
                .build(true)
                .toUri();

        return restTemplate.getForObject(uri, Map.class);
    }

    @Cacheable(value = "movie", key = "#imdbId")
    public Map getByImdbId(String imdbId) {

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("apikey", apiKey)
                .queryParam("i", imdbId)
                .queryParam("plot", "full")
                .build(true)
                .toUri();

        return restTemplate.getForObject(uri, Map.class);
    }
}
