package com.waes.comparison.integration.core.repositories;

import com.waes.comparison.ComparisonApplicationTest;
import com.waes.comparison.core.domain.Position;
import com.waes.comparison.core.entities.JsonFile;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileDTO;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileRequestDTO;
import com.waes.comparison.infrastructure.mapper.JsonFileMapper;
import com.waes.comparison.utils.JsonFileUtils;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonFileCustomRepositoryImplTest extends ComparisonApplicationTest {

    @Test
    public void should_create_new_data_with_left_side_file() {
        JsonFileRequestDTO requestDTO = new JsonFileRequestDTO();
        requestDTO.setEncodedJsonFile(JsonFileUtils.getEncodedFile1());
        JsonFileDTO dto = JsonFileMapper.fillJsonFileDTO(-6l, requestDTO, Position.LEFT);
        jsonFileCustomRepository.createOrUpdate(dto);

        JsonFile storedFile = jsonFileRepository.findById(-6l).get();
        assertEquals(-6l, storedFile.getId());
        assertNull(storedFile.getEncodedRightSide());
        assertNotNull(storedFile.getEncodedLeftSide());

        jsonFileRepository.deleteById(-6l);
    }

    @Test
    public void should_create_new_data_with_right_side_file() {
        JsonFileRequestDTO requestDTO = new JsonFileRequestDTO();
        requestDTO.setEncodedJsonFile(JsonFileUtils.getEncodedFile2());
        JsonFileDTO dto = JsonFileMapper.fillJsonFileDTO(-7l, requestDTO, Position.RIGHT);
        jsonFileCustomRepository.createOrUpdate(dto);

        JsonFile storedFile = jsonFileRepository.findById(-7l).get();
        assertEquals(-7l, storedFile.getId());
        assertNotNull(storedFile.getEncodedRightSide());
        assertNull(storedFile.getEncodedLeftSide());

        jsonFileRepository.deleteById(-7l);
    }

    @Test
    public void should_create_new_data_with_both_side_file() {
        JsonFileRequestDTO requestDTO = new JsonFileRequestDTO();
        requestDTO.setEncodedJsonFile(JsonFileUtils.getEncodedFile2());

        JsonFileDTO rightDTO = JsonFileMapper.fillJsonFileDTO(-8l, requestDTO, Position.RIGHT);
        jsonFileCustomRepository.createOrUpdate(rightDTO);

        JsonFileDTO leftDTO = JsonFileMapper.fillJsonFileDTO(-8l, requestDTO, Position.LEFT);
        jsonFileCustomRepository.createOrUpdate(leftDTO);

        JsonFile storedFile = jsonFileRepository.findById(-8l).get();
        assertEquals(-8l, storedFile.getId());
        assertNotNull(storedFile.getEncodedRightSide());
        assertNotNull(storedFile.getEncodedLeftSide());

        jsonFileRepository.deleteById(-8l);
    }
}
