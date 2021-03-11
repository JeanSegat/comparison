package com.waes.comparison.integration.core.repositories;

import com.waes.comparison.ComparisonApplicationTests;
import com.waes.comparison.core.entities.JsonFile;
import com.waes.comparison.core.usecases.JsonFileUseCases;
import com.waes.comparison.core.usecases.JsonFileUseCasesImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

public class JsonFileRepositoryTest extends ComparisonApplicationTests {
    private final JsonFileUseCases useCases = new JsonFileUseCasesImpl();
    private final String file1 = "{\"name\":\"john\",\"age\":22,\"city\":\"Joinville\",\"country\":\"Brazil\"}";
    private final String file2 = "{\"name\":\"jean\",\"age\":29,\"city\":\"Joinville\",\"country\":\"Brazil\"}";
    private final String file3 = "{\"name\":\"Maria\",\"age\":34,\"city\":\"Eindhoven\",\"country\":\"Netherlands\"}";
    private final String differentOffset = "10 11 22";

    private String encodedFile1;
    private String encodedFile2;
    private String encodedFile3;

    @Before
    public void setUp(){
        encodedFile1 = Base64.getEncoder().encodeToString(file1.getBytes());
        encodedFile2 = Base64.getEncoder().encodeToString(file2.getBytes());
        encodedFile3 = Base64.getEncoder().encodeToString(file3.getBytes());
    }

    @Test
    public void should_store_json_file_with_right_side() {
        JsonFile file = new JsonFile(-1l);
        file.setEncodedRightSide(encodedFile1);
        jsonFileRepository.save(file);

        JsonFile storedFile = jsonFileRepository.findById(-1l).get();

        assertEquals(-1, storedFile.getId());
        assertNotNull(storedFile.getEncodedRightSide());
        assertNull(storedFile.getEncodedLeftSide());

        jsonFileRepository.deleteById(-1l);
    }

    @Test
    public void should_store_json_file_with_left_side() {
        JsonFile file = new JsonFile(-2l);
        file.setEncodedLeftSide(encodedFile2);
        jsonFileRepository.save(file);

        JsonFile storedFile = jsonFileRepository.findById(-2l).get();

        assertEquals(-2, storedFile.getId());
        assertNotNull(storedFile.getEncodedLeftSide());
        assertNull(storedFile.getEncodedRightSide());

        jsonFileRepository.deleteById(-2l);
    }

    @Test
    public void should_store_json_file_with_both_side() {
        JsonFile file = new JsonFile(-3l);
        file.setEncodedLeftSide(encodedFile2);
        file.setEncodedRightSide(encodedFile3);
        jsonFileRepository.save(file);

        JsonFile storedFile = jsonFileRepository.findById(-3l).get();

        assertEquals(-3, storedFile.getId());
        assertNotNull(storedFile.getEncodedLeftSide());
        assertNotNull(storedFile.getEncodedRightSide());

        jsonFileRepository.deleteById(-3l);
    }

    @Test
    public void should_store_json_file_with_both_side_update() {
        JsonFile file = new JsonFile(-4l);
        file.setEncodedLeftSide(encodedFile2);
        file = jsonFileRepository.save(file);
        assertEquals(-4, file.getId());
        assertNotNull(file.getEncodedLeftSide());
        assertNull(file.getEncodedRightSide());

        file.setEncodedRightSide(encodedFile3);
        jsonFileRepository.save(file);

        JsonFile storedFile = jsonFileRepository.findById(-4l).get();

        assertEquals(-4, storedFile.getId());
        assertNotNull(storedFile.getEncodedLeftSide());
        assertNotNull(storedFile.getEncodedRightSide());

        jsonFileRepository.deleteById(-4l);
    }
}
