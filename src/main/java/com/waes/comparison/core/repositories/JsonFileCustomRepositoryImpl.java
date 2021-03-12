package com.waes.comparison.core.repositories;

import com.waes.comparison.core.entities.JsonFile;
import com.waes.comparison.core.exception.FileNotFoundException;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileDTO;
import org.springframework.stereotype.Component;

@Component
public class JsonFileCustomRepositoryImpl implements JsonFileCustomRepository {
    private final JsonFileRepository jsonFileRepository;

    public JsonFileCustomRepositoryImpl(final JsonFileRepository jsonFileRepository) {
        this.jsonFileRepository = jsonFileRepository;
    }

    @Override
    public void createOrUpdate(JsonFileDTO jsonFileDTO) {
        JsonFile jsonFileToSave;

        try {
            jsonFileToSave = findById(jsonFileDTO.getId());
        } catch (RuntimeException e){
            jsonFileToSave = new JsonFile(jsonFileDTO.getId());
        }

        merge(jsonFileDTO, jsonFileToSave);
        jsonFileRepository.save(jsonFileToSave);
    }

    @Override
    public JsonFile findById(final Long id) throws FileNotFoundException {
        return jsonFileRepository.findById(id).orElseThrow( () -> new FileNotFoundException("File not found"));
    }

    private void merge(final JsonFileDTO dto, JsonFile entity) {
        if (dto.getEncodedLeftSide() != null)
            entity.setEncodedLeftSide(dto.getEncodedLeftSide());
        if (dto.getEncodedRightSide() != null)
            entity.setEncodedRightSide(dto.getEncodedRightSide());
    }
}
