package com.sewciety.backend.services;

import com.sewciety.backend.entity.SbsOnProgress;
import com.sewciety.backend.repositories.SbsOnProgressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SbsOnProgressService {

    @Autowired
    private SbsOnProgressRepository sbsOnProgressRepository;

    public SbsOnProgress creatNewProgress(SbsOnProgress progress) {
        return sbsOnProgressRepository.save(progress);
    }

    public SbsOnProgress updateProgress(Integer sbsId,
            Boolean progress) {
        SbsOnProgress sbsProgress = sbsOnProgressRepository.findBySbsId(sbsId).get();
        sbsProgress.setProgress(progress);
        return sbsOnProgressRepository.save(sbsProgress);
    }
}
