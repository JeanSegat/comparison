package com.waes.comparison.unit.core.useCases;

import com.waes.comparison.core.domain.Status;
import com.waes.comparison.core.usecases.ComparisonUseCases;
import com.waes.comparison.core.usecases.ComparisonUseCasesImpl;
import com.waes.comparison.utils.JsonFileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class ComparisonCasesImplTest {

    private final ComparisonUseCases useCases = new ComparisonUseCasesImpl();
    private final String DIFFERENT_OFFSET = "10 11 22";

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
    public void should_return_status_same_file() {
        Status status = useCases.getDiffStatus(encodedFile1, encodedFile1);
        assertEquals(Status.SAME_FILE, status);
    }

    @Test
    public void should_return_status_different_size() {
        Status status = useCases.getDiffStatus(encodedFile1, encodedFile3);
        assertEquals(Status.DIFFERENT_SIZE, status);
    }

    @Test
    public void should_return_status_same_size_different_file() {
        Status status = useCases.getDiffStatus(encodedFile1, encodedFile2);
        assertEquals(Status.SAME_SIZE_DIFFERENT_FILE, status);
    }

    @Test
    public void should_return_different_offsets() {
        String difference = useCases.getDifferentOffset(encodedFile1, encodedFile2);
        assertEquals(DIFFERENT_OFFSET, difference);
    }

    @Test
    public void should_return_with_no_differences() {
        String difference = useCases.getDifferentOffset(encodedFile1, encodedFile1);
        assertEquals("",difference);
    }
}
