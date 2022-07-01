package com.sewciety.backend.repositories;

import java.util.List;

import com.sewciety.backend.entity.SbsImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SbsImageRepository extends JpaRepository<SbsImage, Integer> {

    List<SbsImage> findAllBySbsId(Integer id);

    @Query(value = "SELECT image FROM SbsImage image WHERE image.stepId = :stepId")
    SbsImage findByStepId(Integer stepId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM sbs_images WHERE sbs_id = :sbsId", nativeQuery = true)
    void deleteBySbsId(Integer sbsId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM sbs_images WHERE step_id = :stepId", nativeQuery = true)
    void deleteByStepId(Integer stepId);
}
