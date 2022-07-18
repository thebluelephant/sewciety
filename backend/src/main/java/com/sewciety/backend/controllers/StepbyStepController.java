package com.sewciety.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sewciety.backend.entity.FeNewStepByStep;
import com.sewciety.backend.entity.FePublishAndSavedSbs;
import com.sewciety.backend.services.StepByStepService;

@RestController
@RequestMapping("/api/stepbystep")
public class StepbyStepController {

    @Autowired
    private StepByStepService stepByStepService;

    @PostMapping("/create")
    Integer postNewStepByStep(@RequestBody FeNewStepByStep feStepByStep) {
        return stepByStepService.postNewStepByStep(feStepByStep);
    }

    @RequestMapping("/findAllSbs/{patternId}/{userId}")
    public FePublishAndSavedSbs findAllSbsById(@PathVariable("patternId") Integer patternId,
            @PathVariable("userId") String userId) {
        return stepByStepService.getListOfStepByStepByPatternId(patternId, userId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSbs(@PathVariable("id") Integer sbsId) {
        stepByStepService.deleteSbs(sbsId);
    }

}
