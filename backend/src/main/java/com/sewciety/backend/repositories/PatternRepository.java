package com.sewciety.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.sewciety.backend.entity.Pattern;

@Repository
public interface PatternRepository extends JpaRepository<Pattern, Long> {

    @Query(value = "SELECT DISTINCT p.brand from Pattern p")
    List<String> getBrands();

    @Query(value = "SELECT p from Pattern p WHERE p.name LIKE %:input% OR p.brand LIKE %:input%")
    List<Pattern> findPatternByInput(Optional<String> input);

    @Query(value = "SELECT p from Pattern p WHERE p.brand = :brand AND p.name LIKE %:input%")
    List<Pattern> findPatternByInputAndBrand(Optional<String> input, Optional<String> brand);

    List<Pattern> findPatternByBrandContaining(Optional<String> brand);

    @Query(value = "SELECT p from Pattern p WHERE p.id = :id ")
    Pattern findPatternById(Integer id);
}
