package com.waes.comparison.core.repositories;

import com.waes.comparison.core.entities.Comparison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComparisonRepository extends JpaRepository<Comparison, Long> {
}
