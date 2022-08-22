package com.sewciety.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.sewciety.backend.entity.FePatternSteps;
import com.sewciety.backend.entity.PatternStep;
import com.sewciety.backend.services.PatternStepService;

@RestController
@RequestMapping("/api/patternstep")
public class PatternStepController {

    @Autowired
    private PatternStepService patternStepService;

    @PostMapping(value = "/editSteps")
    List<PatternStep> postMultipleSteps(@RequestParam("steps") List<String> patternSteps)
            throws IOException {
        Gson gson = new Gson();
        List<PatternStep> deserializedPatternSteps = new ArrayList<>();

        int index = 0;
        for (String patternStep : patternSteps) {
            // For each patternStep string, we re-convert it to Json
            PatternStep deserializedStep = gson.fromJson(patternStep, PatternStep.class);

            // And we check if the title has a value before pushing it to the
            // deserializedPatternSteps array
            // to be sure to save Json with data in the DB
            if (deserializedStep.getTitle() != null) {
                deserializedPatternSteps.add(deserializedStep);
            }
            index++;
        }
        return patternStepService.postPatternsSteps(deserializedPatternSteps);
    };

    @RequestMapping("/findAllSteps/{id}")
    public List<PatternStep> getListOfStepsBySbsId(@PathVariable("id") Integer sbsId) {
        return patternStepService.getListOfStepsBySbsId(sbsId);
    }

    @DeleteMapping("/{id}")
    public void deletePatternStep(@PathVariable("id") Integer patternStepId) {
        patternStepService.deletePatternStep(patternStepId);
    }
}
