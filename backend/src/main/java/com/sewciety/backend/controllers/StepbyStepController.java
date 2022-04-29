package com.sewciety.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.sewciety.backend.entity.FeNewStepByStep;
import com.sewciety.backend.entity.StepByStep;
import com.sewciety.backend.services.StepByStepService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "https://localhost:8080")
@RestController
@RequestMapping("/api/stepbystep")
public class StepbyStepController {

    @Autowired
    private StepByStepService stepByStepService;

    @PostMapping("/create")
    Integer postNewStepByStep(@RequestBody FeNewStepByStep feStepByStep) {
        return stepByStepService.postNewStepByStep(feStepByStep);
    }

    @RequestMapping("/findAllSbs/{id}")
    public List<StepByStep> findAllSbsById(@PathVariable("id") Integer id) {
        return stepByStepService.getListOfStepByStepByPatternId(id);
    }

    
}
