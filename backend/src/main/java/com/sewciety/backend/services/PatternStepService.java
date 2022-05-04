package com.sewciety.backend.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sewciety.backend.entity.FePatternSteps;
import com.sewciety.backend.entity.PatternStep;
import com.sewciety.backend.entity.SbsImage;
import com.sewciety.backend.repositories.PatternStepRepository;
import com.sewciety.backend.repositories.SbsImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PatternStepService {

    @Autowired
    private PatternStepRepository patternStepRepository;
    @Autowired
    private SbsImageRepository sbsImageRepository;

    public PatternStep postPatternStep(PatternStep patternStep) {

        return patternStepRepository.save(patternStep);

    }

    public List<PatternStep> postPatternsSteps(List<PatternStep> formatedSteps, MultipartFile[] images) {

        for (int i = 0; i < formatedSteps.size(); i++) {
            PatternStep savedStep = patternStepRepository.save(formatedSteps.get(i));
            SbsImage stepImage = new SbsImage();

            try {
                stepImage.setImage(images[i].getBytes());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            stepImage.setStepId(savedStep.getId());
            stepImage.setSbsId(savedStep.getSbsId());
            sbsImageRepository.save(stepImage);

        }
        return patternStepRepository.getListOfStepsBySbsId(formatedSteps.get(0).getSbsId());
    }

    public FePatternSteps getListOfStepsBySbsId(Integer id) {
        List<PatternStep> steps = patternStepRepository.getListOfStepsBySbsId(id);
        List<SbsImage> images = sbsImageRepository.findAllBySbsId(id);
        return new FePatternSteps(steps, images);
    }

}