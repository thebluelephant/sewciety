package com.sewciety.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sewciety.backend.entity.StepByStep;

@Repository
public interface StepByStepRepository extends JpaRepository<StepByStep, Integer> {

}
