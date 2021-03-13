package com.waes.comparison.integration.core.repositories;

import com.waes.comparison.ComparisonApplicationTest;
import com.waes.comparison.core.entities.Comparison;
import com.waes.comparison.utils.JsonFileUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComparisonRepositoryTest extends ComparisonApplicationTest {

    private String encodedFile1;
    private String encodedFile2;
    private String encodedFile3;

    @Before
    public void setUp(){
        encodedFile1 = JsonFileUtils.getEncodedFile1();
        encodedFile2 = JsonFileUtils.getEncodedFile2();
        encodedFile3 = JsonFileUtils.getEncodedFile3();
    }

    @Test
    public void should_store_json_file_with_right_side() {
        Comparison file = new Comparison(-1l);
        file.setEncodedRightSide(encodedFile1);
        comparisonRepository.save(file);

        Comparison storedFile = comparisonRepository.findById(-1l).get();

        assertEquals(-1, storedFile.getId());
        assertNotNull(storedFile.getEncodedRightSide());
        assertNull(storedFile.getEncodedLeftSide());

        comparisonRepository.deleteById(-1l);
    }

    @Test
    public void should_store_json_file_with_left_side() {
        Comparison file = new Comparison(-2l);
        file.setEncodedLeftSide(encodedFile2);
        comparisonRepository.save(file);

        Comparison storedFile = comparisonRepository.findById(-2l).get();

        assertEquals(-2, storedFile.getId());
        assertNotNull(storedFile.getEncodedLeftSide());
        assertNull(storedFile.getEncodedRightSide());

        comparisonRepository.deleteById(-2l);
    }

    @Test
    public void should_store_json_file_with_both_side() {
        Comparison file = new Comparison(-3l);
        file.setEncodedLeftSide(encodedFile2);
        file.setEncodedRightSide(encodedFile3);
        comparisonRepository.save(file);

        Comparison storedFile = comparisonRepository.findById(-3l).get();

        assertEquals(-3, storedFile.getId());
        assertNotNull(storedFile.getEncodedLeftSide());
        assertNotNull(storedFile.getEncodedRightSide());

        comparisonRepository.deleteById(-3l);
    }

    @Test
    public void should_store_json_file_with_both_side_update() {
        Comparison file = new Comparison(-4l);
        file.setEncodedLeftSide(encodedFile2);
        file = comparisonRepository.save(file);
        assertEquals(-4, file.getId());
        assertNotNull(file.getEncodedLeftSide());
        assertNull(file.getEncodedRightSide());

        file.setEncodedRightSide(encodedFile3);
        comparisonRepository.save(file);

        Comparison storedFile = comparisonRepository.findById(-4l).get();

        assertEquals(-4, storedFile.getId());
        assertNotNull(storedFile.getEncodedLeftSide());
        assertNotNull(storedFile.getEncodedRightSide());

        comparisonRepository.deleteById(-4l);
    }
}
