package com.sewciety.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sewciety.backend.services.PatternService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.sewciety.backend.entity.Pattern;

@RestController
@RequestMapping("/api/pattern")
public class PatternController {

    @Autowired
    private PatternService patternService;

    @GetMapping("/all")
    public List<Pattern> getPatterns() {
        return patternService.findAll(); // Returns all patterns!
    }

    @RequestMapping("/{id}")
    public Pattern findPatternById(@PathVariable("id") Integer id) {
        return patternService.findPatternById(id);
    }

    @GetMapping("/brands")
    public List<String> find() {
        return patternService.getBrands();
    }

    @GetMapping()
    public List<Pattern> findPatternByNameOrBrand(@RequestParam("input") Optional<String> input,
            @RequestParam("brand") Optional<String> brand) {
        if (brand.isEmpty()) {
            return patternService.findPatternByInput(input);
        } else if (input.isEmpty()) {
            return patternService.findPatternByBrandContaining(brand);
        } else {
            return patternService.findPatternByInputAndBrand(input, brand);
        }
    }

    @PostMapping("/new")
    Pattern submitNewPattern(@RequestBody Pattern pattern) throws IOException {
        return patternService.submitNewPattern(pattern);
    }

}