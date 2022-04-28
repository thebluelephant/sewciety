package com.sewciety.backend.repositories;

import com.sewciety.backend.entity.SbsOnProgress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SbsOnProgressRepository extends JpaRepository<SbsOnProgress, Integer> {

    SbsOnProgress save(SbsOnProgress sbsProgress);

}