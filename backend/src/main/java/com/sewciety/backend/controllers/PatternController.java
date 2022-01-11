package com.sewciety.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sewciety.backend.repositories.PatternRepository;
import java.util.List;
import java.util.Optional;

import com.sewciety.backend.entity.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/pattern")
public class PatternController {

    @Autowired
    private PatternRepository patternRepository;

    @GetMapping("/all")
    public List<Pattern> getPatterns() {
        return patternRepository.findAll(); // Returns all patterns!
    }

    @GetMapping()
    public List<Pattern> findPatternByNameOrBrand(@RequestParam("input") Optional<String> input,
            @RequestParam("brand") Optional<String> brand) {
        if (brand.isEmpty()) {
            return patternRepository.findPatternByInput(input);
        } else if (input.isEmpty()) {
            return patternRepository.findPatternByBrandContaining(brand);
        } else {
            return patternRepository.findPatternByInputAndBrand(input, brand);
        }

    }

}