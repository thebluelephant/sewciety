package com.sewciety.backend.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.sewciety.backend.entity.PatternStep;
import com.sewciety.backend.repositories.PatternStepRepository;
import com.sewciety.backend.utils.ImageUtils;
import com.sewciety.backend.utils.GoogleCloudStorage.GoogleCloudStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatternStepService {

    @Autowired
    private PatternStepRepository patternStepRepository;

    public PatternStep postPatternStep(PatternStep patternStep) {
        return patternStepRepository.save(patternStep);
    }

    public List<PatternStep> postPatternsSteps(List<PatternStep> formatedSteps)
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

                    // If the step.getImage() is a base64, it means the user upload a new image (and
                    // didn't update the initial image with another one,
                    // for example when he deletes a step and the previous already-saved images are
                    // moving from one step to another)
                    // So if the image is a base64, We delete the image in GCP before uploading the
                    // new one to have its new URL.
                    if (ImageUtils.isBase64Encoded(step.getImage())) {
                        String imageName = GoogleCloudStorage.getFileNameByUrl(oldStep.getImage());
                        GoogleCloudStorage.deleteImage("sewciety-patternsteps-images", imageName);
                        String ImageUrl = GoogleCloudStorage.uploadImage(step.getImage(),
                                "sewciety-patternsteps-images");
                        oldStep.setImage(ImageUrl);
                    }
                    patternStepRepository.save(oldStep);
                }
            } else {
                String ImageUrl = GoogleCloudStorage.uploadImage(step.getImage(), "sewciety-patternsteps-images");
                step.setImage(ImageUrl);
                patternStepRepository.save(step);
            }
        }
        return patternStepRepository.findAllBySbsId(formatedSteps.get(0).getSbsId());
    }

    public List<PatternStep> getListOfStepsBySbsId(Integer id) {
        return patternStepRepository.findAllBySbsId(id);
    }

    public void deletePatternStep(Integer patternStepId) {
        PatternStep patternStep = patternStepRepository.getById(patternStepId);
        String imageName = GoogleCloudStorage.getFileNameByUrl(patternStep.getImage());
        GoogleCloudStorage.deleteImage("sewciety-patternsteps-images", imageName);
        patternStepRepository.deleteById(patternStepId);
    }

    @Transactional
    @Modifying
    public void deleteAllPatternStepBySbsId(Integer sbsId) {
        List<PatternStep> steps = getListOfStepsBySbsId(sbsId);
        steps.forEach((step) -> {
            String imageName = GoogleCloudStorage.getFileNameByUrl(step.getImage());
            GoogleCloudStorage.deleteImage("sewciety-patternsteps-images", imageName);
        });
        patternStepRepository.deleteAllPatternStepBySbsId(sbsId);

    }

}