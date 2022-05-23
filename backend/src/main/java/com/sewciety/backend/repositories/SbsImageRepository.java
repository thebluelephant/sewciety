package com.sewciety.backend.repositories;

import java.util.List;

import com.sewciety.backend.entity.SbsImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SbsImageRepository extends JpaRepository<SbsImage, Integer> {

    List<SbsImage> findAllBySbsId(Integer id);

    @Query(value = "SELECT image FROM SbsImage image WHERE image.stepId = :stepId")
    SbsImage findByStepId(Integer stepId);
}
