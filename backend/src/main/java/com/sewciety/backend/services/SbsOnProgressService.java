package com.sewciety.backend.services;

import java.util.Optional;

import javax.management.relation.RelationNotFoundException;

import com.sewciety.backend.entity.SbsOnProgress;
import com.sewciety.backend.repositories.SbsOnProgressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SbsOnProgressService {

    @Autowired
    private SbsOnProgressRepository sbsOnProgressRepository;

    public SbsOnProgress creatNewProgress(SbsOnProgress progress) {
        return sbsOnProgressRepository.save(progress);
    }

    public ResponseEntity<SbsOnProgress> updateProgress(Integer progressId,
            Optional<Boolean> progress)
            throws RelationNotFoundException {
        Optional<SbsOnProgress> sbsProgress = sbsOnProgressRepository.findById(progressId);

        sbsProgress.ifPresent(foundedSbsProgress -> {
            progress.ifPresent(progressVal -> {
                foundedSbsProgress.setProgress(progressVal);
            });
            sbsOnProgressRepository.save(foundedSbsProgress);
        });
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
