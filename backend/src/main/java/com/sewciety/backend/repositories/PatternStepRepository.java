package com.sewciety.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

import com.sewciety.backend.entity.PatternStep;

@Repository
public interface PatternStepRepository extends JpaRepository<PatternStep, Integer> {

    List<PatternStep> findAllBySbsId(Integer id);

    @Transactional 
    @Query(value = "DELETE FROM pattern_step WHERE sbs_id = :sbsId", nativeQuery = true)
    void deleteAllPatternStepBySbsId(Integer sbsId);

}
