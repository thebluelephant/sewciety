package com.sewciety.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.sewciety.backend.entity.StepByStep;

@Repository
public interface StepByStepRepository extends JpaRepository<StepByStep, Integer> {

    @Query(value = "SELECT sbs FROM StepByStep sbs inner join SbsOnProgress sbsProgress on (sbs.patternId = sbsProgress.patternId) WHERE sbsProgress.progress = 1 AND sbs.patternId = :patternId AND sbs.authorId = :userId GROUP BY sbsProgress.sbsId")
    List<StepByStep> getListOfSavedSbsByUserIdAndPatternId(Integer patternId, String userId);

    @Query(value = "SELECT sbs FROM StepByStep sbs inner join SbsOnProgress sbsProgress on (sbs.patternId = sbsProgress.patternId) WHERE sbsProgress.progress = 0 AND sbs.patternId = :patternId GROUP BY sbsProgress.sbsId")
    List<StepByStep> getListOfPublishedSbsByPatternId(Integer patternId);

}
