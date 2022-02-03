package com.sewciety.backend.controllers;

import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sewciety.backend.repositories.PatternRepository;
import java.util.List;
import java.util.Optional;

import com.sewciety.backend.entity.Pattern;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/pattern")
public class PatternController {

    @Autowired
    private PatternRepository patternRepository;

    @GetMapping("/all")
    public List<Pattern> getPatterns() {
        return patternRepository.findAll(); // Returns all patterns!
    }

    @GetMapping("/brands")
    public List<String> find() {
        return patternRepository.getBrands();
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