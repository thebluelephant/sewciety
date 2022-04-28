package com.sewciety.backend.services;

import java.util.List;

import com.sewciety.backend.entity.PatternStep;
import com.sewciety.backend.repositories.PatternStepRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatternStepService {

    @Autowired
    private PatternStepRepository patternStepRepository;

    public PatternStep postPatternStep(PatternStep patternStep) {

        return patternStepRepository.save(patternStep);

    }

    public List<PatternStep> postPatternsSteps(List<PatternStep> formatedSteps) {
        return patternStepRepository.saveAll(formatedSteps);
    }
}