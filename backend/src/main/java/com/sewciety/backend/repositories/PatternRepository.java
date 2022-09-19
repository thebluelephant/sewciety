package com.sewciety.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.sewciety.backend.entity.Pattern;

@Repository
public interface PatternRepository extends JpaRepository<Pattern, Integer> {

    List<Pattern> findAll();

    @Query(value = "SELECT DISTINCT p.brand from Pattern p WHERE p.verified = 1")
    List<String> getBrands();

    @Query(value = "SELECT p from Pattern p WHERE p.verified = 1 AND (p.name LIKE %:input% OR p.brand LIKE %:input%)")
    Page<Pattern> findPatternByInput(Optional<String> input, Pageable pageable);

    @Query(value = "SELECT p from Pattern p WHERE p.brand = :brand AND p.verified = 1 AND p.name LIKE %:input%")
    Page<Pattern> findPatternByInputAndBrand(Optional<String> input, Optional<String> brand, Pageable pageable);

    @Query(value = "SELECT p from Pattern p WHERE p.brand = :brand AND p.verified = 1")
    Page<Pattern> findPatternByBrand(Optional<String> brand, Pageable pageable);

    @Query(value = "SELECT p from Pattern p WHERE p.id = :id ")
    Pattern findPatternById(Integer id);
}
