package com.omdb.controller;

import com.omdb.service.OmdbService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class OmdbController {

    @Autowired
    private OmdbService service;

    @GetMapping("/search")
    public ResponseEntity<?> search(
        @RequestParam @NotBlank String title,
        @RequestParam(required = false, defaultValue = "movie") String type,
        @RequestParam(required = false, defaultValue = "1") @Min(1) int page
    ) {
        Map result = service.searchByTitle(title, type, page);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/movie/{imdbId}")
    public ResponseEntity<?> movie(@PathVariable String imdbId) {
        Map result = service.getByImdbId(imdbId);
        return ResponseEntity.ok(result);
    }
}