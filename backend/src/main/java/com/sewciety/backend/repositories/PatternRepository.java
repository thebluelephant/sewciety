package com.sewciety.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sewciety.backend.entity.Pattern;

public interface PatternRepository extends JpaRepository<Pattern, Long> {

}
