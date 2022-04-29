package com.sewciety.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.sewciety.backend.entity.PatternStep;

@Repository
public interface PatternStepRepository extends JpaRepository<PatternStep, Integer> {

    @Query(value = "SELECT step from PatternStep step WHERE step.sbsId = :id ")
    List<PatternStep> getListOfStepsBySbsId(Integer id);

}
