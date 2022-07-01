package com.sewciety.backend.repositories;

import java.util.Optional;

import com.sewciety.backend.entity.SbsOnProgress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SbsOnProgressRepository extends JpaRepository<SbsOnProgress, Integer> {

    SbsOnProgress save(SbsOnProgress sbsProgress);

    @Query(value = "SELECT progress FROM SbsOnProgress progress WHERE progress.sbsId = :sbsId")    
    Optional<SbsOnProgress> findBySbsId(Integer sbsId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM sbs_onprogress WHERE sbs_id = :sbsId", nativeQuery = true)
    void deleteBySbsId(Integer sbsId);
    

}