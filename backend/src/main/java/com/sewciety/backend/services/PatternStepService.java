package com.sewciety.backend.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    public List<PatternStep> postPatternsSteps(List<PatternStep> formatedSteps, List<MultipartFile> imageFiles)
            throws IOException {

        for (int i = 0; i < formatedSteps.size(); i++) {
            PatternStep step = formatedSteps.get(i);

            // If we get the step ID from the FE, it means the step already exist in DB so
            // we just have to modify it
            if (step.getId() != null) {
                Optional<PatternStep> existingStep = patternStepRepository.findById(step.getId());

                if (existingStep.isPresent()) {
                    PatternStep oldStep = existingStep.get();
                    oldStep.setTitle(step.getTitle());
                    oldStep.setExplanations(step.getExplanations());
                    patternStepRepository.save(oldStep);
                    SbsImage oldImage = sbsImageRepository.findByStepId(step.getId());
                    try {
                        oldImage.setImage(imageFiles.get(i).getBytes());
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    sbsImageRepository.save(oldImage);
                }
            } else {
                PatternStep savedStep = patternStepRepository.save(step);
                SbsImage stepImage = new SbsImage();

                try {
                    stepImage.setImage(imageFiles.get(i).getBytes());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                stepImage.setStepId(savedStep.getId());
                stepImage.setSbsId(savedStep.getSbsId());

                sbsImageRepository.save(stepImage);
            }

        }
        return patternStepRepository.findAllBySbsId(formatedSteps.get(0).getSbsId());
    }

    public FePatternSteps getListOfStepsBySbsId(Integer id) {
        List<PatternStep> steps = patternStepRepository.findAllBySbsId(id);
        List<SbsImage> images = sbsImageRepository.findAllBySbsId(id);
        return new FePatternSteps(steps, images);
    }

    public void deletePatternStep(Integer patternStepId) {
        sbsImageRepository.deleteByStepId(patternStepId);
        patternStepRepository.deleteById(patternStepId);
        
    }

    public void deleteAllPatternStepBySbsId(Integer sbsId){
        patternStepRepository.deleteAllPatternStepBySbsId(sbsId);
        
    }

}