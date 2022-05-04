package com.sewciety.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sewciety.backend.entity.FePatternSteps;
import com.sewciety.backend.entity.PatternStep;
import com.sewciety.backend.services.PatternStepService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "https://localhost:8080")
@RestController
@RequestMapping("/api/patternstep")
public class PatternStepController {

    @Autowired
    private PatternStepService patternStepService;

    @PostMapping(value = "/newSteps", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<PatternStep> postMultipleSteps(@RequestParam("steps") List<String> patternSteps,
            @RequestParam("images") MultipartFile[] images)
            throws IOException {
        
        List<PatternStep> deserializedPatternSteps = new ArrayList<>();
        for (String patternStep : patternSteps) {
            Gson gson = new Gson();
            deserializedPatternSteps.add(gson.fromJson(patternStep, PatternStep.class));
        }
        return patternStepService.postPatternsSteps(deserializedPatternSteps, images);
    };

    @RequestMapping("/findAllSteps/{id}")
    public FePatternSteps getListOfStepsBySbsId(@PathVariable("id") Integer id) {
        return patternStepService.getListOfStepsBySbsId(id);
    }
}
