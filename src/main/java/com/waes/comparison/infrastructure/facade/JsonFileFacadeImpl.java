package com.waes.comparison.infrastructure.facade;

import com.waes.comparison.core.domain.Status;
import com.waes.comparison.core.entities.JsonFile;
import com.waes.comparison.core.repositories.JsonFileCustomRepository;
import com.waes.comparison.core.usecases.JsonFileUseCases;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileDTO;

public class JsonFileFacadeImpl implements JsonFileFacade {

    private final JsonFileCustomRepository jsonFileCustomRepository;

    private final JsonFileUseCases jsonFileUseCases;

    private static final String SAME_FILE = "Same File";
    private static final String DIFFERENT_SIZE = "Different Size";

    public JsonFileFacadeImpl(final JsonFileCustomRepository jsonFileCustomRepository, JsonFileUseCases jsonFileUseCases) {
        this.jsonFileCustomRepository = jsonFileCustomRepository;
        this.jsonFileUseCases = jsonFileUseCases;
    }


    @Override
    public void createOrUpdate(JsonFileDTO dto) {
        jsonFileCustomRepository.createOrUpdate(dto);
    }

    @Override
    public String getDifferenceStatus(Long id) {
        JsonFile jsonFile = jsonFileCustomRepository.findById(id);
        Status status = jsonFileUseCases.getDiffStatus(jsonFile.getEncodedLeftSide(), jsonFile.getEncodedRightSide());
        String result;

        switch (status) {
            case SAME_FILE: return SAME_FILE;
            case DIFFERENT_SIZE: return DIFFERENT_SIZE;
            default: return jsonFileUseCases.getDifferentOffset(jsonFile.getEncodedLeftSide(), jsonFile.getEncodedRightSide());
        }
    }
}
