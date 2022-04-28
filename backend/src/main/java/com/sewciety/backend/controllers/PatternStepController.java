package com.sewciety.backend.controllers;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sewciety.backend.entity.FePatternStep;
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
    List<PatternStep> postMultipleSteps(@RequestParam("sequences[]") List<Integer> sequences,
            @RequestParam("titles[]") List<String> titles, @RequestParam("sbsId") Integer sbsId,
            @RequestParam("explanations[]") List<String> explanations, @RequestParam("images[]") MultipartFile[] images)
            throws IOException {

        List<PatternStep> formatedSteps = new ArrayList<>();

        for (int i = 0; i < titles.size(); i++) {
            Blob blob;
            PatternStep formatedStep = new PatternStep();
            try {
                blob = new SerialBlob(images[i].getBytes());
                formatedStep.setImage(blob);
            } catch (SerialException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            formatedStep.setSequence(sequences.get(i));
            formatedStep.setSbsId(sbsId);
            formatedStep.setExplanations(explanations.get(i));
            formatedStep.setTitle(titles.get(i));
            formatedSteps.add(formatedStep);
        }
        return patternStepService.postPatternsSteps(formatedSteps);
    };
}
