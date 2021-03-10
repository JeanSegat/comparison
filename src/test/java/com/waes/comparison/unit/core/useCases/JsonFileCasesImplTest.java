package com.waes.comparison.unit.core.useCases;

import com.waes.comparison.core.domain.Status;
import com.waes.comparison.core.usecases.JsonFileUseCases;
import com.waes.comparison.core.usecases.JsonFileUseCasesImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class JsonFileCasesImplTest {

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
        assertEquals(differentOffset, difference);
    }

    @Test
    public void should_return_with_no_differences() {
        String difference = useCases.getDifferentOffset(encodedFile1, encodedFile1);
        assertEquals("",difference);
    }
}
