package com.waes.comparison.core.repositories;

import com.waes.comparison.core.entities.JsonFile;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileDTO;

public interface JsonFileCustomRepository {
    void createOrUpdate(JsonFileDTO jsonFileDTO);

    JsonFile findById(Long id);
}
