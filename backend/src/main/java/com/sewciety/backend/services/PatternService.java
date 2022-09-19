package com.sewciety.backend.services;

import com.sewciety.backend.entity.Pattern;
import com.sewciety.backend.repositories.PatternRepository;
import com.sewciety.backend.utils.GoogleCloudStorage.GoogleCloudStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Pattern> findPatternByInput(Optional<String> input, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return patternRepository.findPatternByInput(input, paging);
    }

    public Page<Pattern> findPatternByInputAndBrand(Optional<String> input, Optional<String> brand, int page,
            int size) {
        Pageable paging = PageRequest.of(page, size);
        return patternRepository.findPatternByInputAndBrand(input, brand, paging);
    }

    public Page<Pattern> findPatternByBrandContaining(Optional<String> brand, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return patternRepository.findPatternByBrand(brand, paging);
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