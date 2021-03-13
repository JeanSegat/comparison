package com.waes.comparison.integration.infrastructure.facade;

import com.waes.comparison.ComparisonApplicationTest;
import com.waes.comparison.core.domain.Position;
import com.waes.comparison.core.entities.Comparison;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileDTO;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileRequestDTO;
import com.waes.comparison.infrastructure.mapper.JsonFileMapper;
import com.waes.comparison.utils.JsonFileUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComparisonFacadeImplTest extends ComparisonApplicationTest {

    private static final String SAME_FILE = "Same File";
    private static final String DIFFERENT_SIZE = "Different Size";
    private final String DIFFERENT_OFFSET = "10 11 22";

    private JsonFileRequestDTO requestDTO1;
    private JsonFileRequestDTO requestDTO2;
    private JsonFileRequestDTO requestDTO3;

    @Before
    public void setUp(){
        requestDTO1 = new JsonFileRequestDTO();
        requestDTO1.setEncodedJsonFile(JsonFileUtils.getEncodedFile1());

        requestDTO2 = new JsonFileRequestDTO();
        requestDTO2.setEncodedJsonFile(JsonFileUtils.getEncodedFile2());

        requestDTO3 = new JsonFileRequestDTO();
        requestDTO3.setEncodedJsonFile(JsonFileUtils.getEncodedFile3());
    }

    @Test
    public void should_create_new_data_with_left_side_file() {
        JsonFileDTO dto = JsonFileMapper.fillJsonFileDTO(-9l, requestDTO1, Position.LEFT);
        jsonFileFacade.createOrUpdate(dto);

        Comparison storedFile = comparisonRepository.findById(-9l).get();
        assertEquals(-9l, storedFile.getId());
        assertNull(storedFile.getEncodedRightSide());
        assertNotNull(storedFile.getEncodedLeftSide());

        comparisonRepository.deleteById(-9l);
    }

    @Test
    public void should_create_new_data_with_right_side_file() {
        JsonFileDTO dto = JsonFileMapper.fillJsonFileDTO(-10l, requestDTO2, Position.RIGHT);
        jsonFileFacade.createOrUpdate(dto);

        Comparison storedFile = comparisonRepository.findById(-10l).get();
        assertEquals(-10l, storedFile.getId());
        assertNotNull(storedFile.getEncodedRightSide());
        assertNull(storedFile.getEncodedLeftSide());

        comparisonRepository.deleteById(-10l);
    }

    @Test
    public void should_create_new_data_with_both_side_file() {
        JsonFileDTO rightDTO = JsonFileMapper.fillJsonFileDTO(-11l, requestDTO2, Position.RIGHT);
        jsonFileFacade.createOrUpdate(rightDTO);

        JsonFileDTO leftDTO = JsonFileMapper.fillJsonFileDTO(-11l, requestDTO2, Position.LEFT);
        jsonFileFacade.createOrUpdate(leftDTO);

        Comparison storedFile = comparisonRepository.findById(-11l).get();
        assertEquals(-11l, storedFile.getId());
        assertNotNull(storedFile.getEncodedRightSide());
        assertNotNull(storedFile.getEncodedLeftSide());

        comparisonRepository.deleteById(-11l);
    }

    @Test
    public void should_return_same_file() {
        JsonFileDTO rightDTO = JsonFileMapper.fillJsonFileDTO(-12l, requestDTO2, Position.RIGHT);
        jsonFileFacade.createOrUpdate(rightDTO);

        JsonFileDTO leftDTO = JsonFileMapper.fillJsonFileDTO(-12l, requestDTO2, Position.LEFT);
        jsonFileFacade.createOrUpdate(leftDTO);

        String result = jsonFileFacade.getDifferenceStatus(-12l);

        assertEquals(SAME_FILE, result);
        comparisonRepository.deleteById(-12l);
    }

    @Test
    public void should_return_different_size() {
        JsonFileDTO rightDTO = JsonFileMapper.fillJsonFileDTO(-13l, requestDTO1, Position.RIGHT);
        jsonFileFacade.createOrUpdate(rightDTO);

        JsonFileDTO leftDTO = JsonFileMapper.fillJsonFileDTO(-13l, requestDTO3, Position.LEFT);
        jsonFileFacade.createOrUpdate(leftDTO);

        String result = jsonFileFacade.getDifferenceStatus(-13l);

        assertEquals(DIFFERENT_SIZE, result);
        comparisonRepository.deleteById(-13l);
    }

    @Test
    public void should_return_different_offset() {
        JsonFileDTO rightDTO = JsonFileMapper.fillJsonFileDTO(-14l, requestDTO1, Position.RIGHT);
        jsonFileFacade.createOrUpdate(rightDTO);

        JsonFileDTO leftDTO = JsonFileMapper.fillJsonFileDTO(-14l, requestDTO2, Position.LEFT);
        jsonFileFacade.createOrUpdate(leftDTO);

        String result = jsonFileFacade.getDifferenceStatus(-14l);

        assertEquals(DIFFERENT_OFFSET, result);
        comparisonRepository.deleteById(-14l);
    }


}
