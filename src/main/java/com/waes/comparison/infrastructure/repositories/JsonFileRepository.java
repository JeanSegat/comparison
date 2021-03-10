package com.waes.comparison.infrastructure.repositories;

import com.waes.comparison.core.entities.JsonFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonFileRepository extends JpaRepository<JsonFile, Long> {
}
