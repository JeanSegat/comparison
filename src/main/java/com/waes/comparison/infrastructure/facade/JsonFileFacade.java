package com.waes.comparison.infrastructure.facade;

import com.waes.comparison.core.exception.FileNotFoundException;
import com.waes.comparison.core.exception.OneFileIsEmptyException;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileDTO;

public interface JsonFileFacade {

    void createOrUpdate(final JsonFileDTO dto);

    String getDifferenceStatus(final Long id) throws FileNotFoundException, OneFileIsEmptyException;
}
