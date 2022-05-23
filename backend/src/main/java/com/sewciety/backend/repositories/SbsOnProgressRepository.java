package com.sewciety.backend.repositories;

import java.util.Optional;

import com.sewciety.backend.entity.SbsOnProgress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SbsOnProgressRepository extends JpaRepository<SbsOnProgress, Integer> {

    SbsOnProgress save(SbsOnProgress sbsProgress);

    @Query(value = "SELECT progress FROM SbsOnProgress progress WHERE progress.sbsId = :sbsId")    
    Optional<SbsOnProgress> findBySbsId(Integer sbsId);

}