package com.sewciety.backend.services;

import java.util.Date;
import java.util.List;

import com.sewciety.backend.entity.FeNewStepByStep;
import com.sewciety.backend.entity.SbsOnProgress;
import com.sewciety.backend.entity.StepByStep;
import com.sewciety.backend.repositories.StepByStepRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StepByStepService {

    @Autowired
    private StepByStepRepository stepByStepRepository;
    @Autowired
    private SbsOnProgressService sbsOnProgressService;

    public Integer postNewStepByStep(FeNewStepByStep feStepByStep) {
        // First we create a StepByStep and post it
        StepByStep newStepByStep = postStepByStep(new StepByStep(feStepByStep.getPatternId(),
                feStepByStep.getAuthorId(), feStepByStep.getAuthorUsername(), new Date()));

        // Then, with the new Step by step created in DB, we create a progress that we
        // automatically close
        // TO DO : Add possibility to set progress value dynamically
        SbsOnProgress progress = new SbsOnProgress(newStepByStep.getPatternId(), newStepByStep.getAuthorId(),
                false, newStepByStep.getId());
        sbsOnProgressService.creatNewProgress(progress);

        // Finally we return the step by step ID automatically created.
        return newStepByStep.getId();

    }

    public StepByStep postStepByStep(StepByStep stepByStep) {
        return stepByStepRepository.save(stepByStep);
    }

    public List<StepByStep> getListOfStepByStepByPatternId(Integer patternId) {
        return stepByStepRepository.findByPatternId(patternId);
    }
}