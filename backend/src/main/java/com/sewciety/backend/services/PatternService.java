package com.sewciety.backend.services;

import com.sewciety.backend.entity.Pattern;
import com.sewciety.backend.repositories.PatternRepository;
import com.sewciety.backend.utils.GoogleCloudStorage.GoogleCloudStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PatternService {
    @Autowired
    private PatternRepository patternRepository;

    public List<Pattern> findAll() {
        return patternRepository.findAll();
    }

    public List<String> getBrands() {
        return patternRepository.getBrands();
    }

    public List<Pattern> findPatternByInput(Optional<String> input) {
        return patternRepository.findPatternByInput(input);
    }

    public List<Pattern> findPatternByInputAndBrand(Optional<String> input, Optional<String> brand) {
        return findPatternByInputAndBrand(input, brand);
    }

    public List<Pattern> findPatternByBrandContaining(Optional<String> brand) {
        return patternRepository.findPatternByBrandContaining(brand);
    }

    public Pattern findPatternById(Integer id) {
        return patternRepository.findPatternById(id);
    }

    public Pattern submitNewPattern(Pattern pattern) throws IOException {
        String ImageUrl = GoogleCloudStorage.uploadImage(pattern.getimage(), "sewciety-pattern-images");
        pattern.setimage(ImageUrl);
        pattern.setVerified(false);
        return patternRepository.save(pattern);
    }
}