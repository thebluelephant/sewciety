package com.sewciety.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sewciety.backend.repositories.PatternRepository;
import java.util.List;
import com.sewciety.backend.entity.Pattern;

@RestController
@RequestMapping("/api/pattern")
public class PatternController {

    @Autowired
    private PatternRepository patternRepository;

    @GetMapping("/all")
    public List<Pattern> getPatterns() {
        return patternRepository.findAll(); // Returns all patterns!
    }

}