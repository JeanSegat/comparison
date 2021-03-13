package com.waes.comparison.core.repositories;

import com.waes.comparison.core.entities.Comparison;
import com.waes.comparison.core.exception.FileNotFoundException;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileDTO;

public interface ComparisonCustomRepository {
    void createOrUpdate(JsonFileDTO jsonFileDTO);

    Comparison findById(Long id) throws FileNotFoundException;
}
