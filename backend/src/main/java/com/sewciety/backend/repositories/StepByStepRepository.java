package com.sewciety.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.sewciety.backend.entity.StepByStep;

@Repository
public interface StepByStepRepository extends JpaRepository<StepByStep, Integer> {

    @Query(value = "SELECT sbs.id, sbs.pattern_id, sbs.author_id, sbs.author_username, sbs.date, sbs.verified, sbsProgress.sbs_id FROM step_by_step sbs inner join sbs_onprogress sbsProgress on (sbs.pattern_id = sbsProgress.pattern_id) INNER JOIN sbs_onprogress on (sbs.id = sbsProgress.sbs_id) WHERE sbsProgress.progress = 1 AND sbs.pattern_id = :patternId AND sbs.author_id = :userId GROUP BY sbsProgress.sbs_id", nativeQuery = true)
    List<StepByStep> getListOfSavedSbsByUserIdAndPatternId(Integer patternId, String userId);

    @Query(value = "SELECT sbs.id, sbs.pattern_id, sbs.author_id, sbs.author_username, sbs.date, sbs.verified, sbsProgress.sbs_id FROM step_by_step sbs inner join sbs_onprogress sbsProgress on (sbs.pattern_id = sbsProgress.pattern_id) INNER JOIN sbs_onprogress on (sbs.id = sbsProgress.sbs_id) WHERE sbsProgress.progress = 0 AND sbs.verified = 1 AND sbs.pattern_id = :patternId GROUP BY sbsProgress.sbs_id", nativeQuery = true)
    List<StepByStep> getListOfPublishedSbsByPatternId(Integer patternId);

}
