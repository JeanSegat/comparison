package com.waes.comparison.integration.infrastructure.entrypoint;

import com.waes.comparison.ComparisonApplicationTest;
import com.waes.comparison.core.entities.Comparison;
import com.waes.comparison.core.exception.ComparisonNotFoundException;
import com.waes.comparison.core.exception.OneFileIsEmptyException;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileRequestDTO;
import com.waes.comparison.utils.JsonFileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertTrue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JsonFileControllerTest extends ComparisonApplicationTest {

    private static String JSON_FILE_BASE_PATH = "/v1/diff";
    private static String JSON_LEFT_FILE_PATH = "/{id}/left";
    private static String JSON_RIGHT_FILE_PATH = "/{id}/right";
    private static final String SAME_FILE = "{\"response\":\"Same File\"}";
    private static final String DIFFERENT_SIZE = "{\"response\":\"Different Size\"}";
    private final String DIFFERENT_OFFSET = "{\"response\":\"Position of difference: 10 11 22\"}";

    private JsonFileRequestDTO requestDTO1;
    private String encodedFile1;
    private String encodedFile2;
    private String encodedFile3;

    @Before
    public void setUp()
    {
        requestDTO1 = new JsonFileRequestDTO();
        requestDTO1.setEncodedJsonFile(JsonFileUtils.getEncodedFile1());

        encodedFile1 = JsonFileUtils.getEncodedFile1();
        encodedFile2 = JsonFileUtils.getEncodedFile2();
        encodedFile3 = JsonFileUtils.getEncodedFile3();
    }

    @Test
    public void should_create_left_file() throws Exception {
        mvc.perform(post(JSON_FILE_BASE_PATH + JSON_LEFT_FILE_PATH.replace("{id}","1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(requestDTO1)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_create_right_file() throws Exception {
        mvc.perform(post(JSON_FILE_BASE_PATH + JSON_RIGHT_FILE_PATH.replace("{id}","1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(requestDTO1)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_return_same_file() throws Exception {
        Comparison file = new Comparison(-50l);
        file.setEncodedRightSide(encodedFile1);
        file.setEncodedLeftSide(encodedFile1);
        comparisonRepository.save(file);

        mvc.perform(get(JSON_FILE_BASE_PATH + "/-50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(SAME_FILE));
    }

    @Test
    public void should_return_different_size() throws Exception {
        Comparison file = new Comparison(-50l);
        file.setEncodedRightSide(encodedFile1);
        file.setEncodedLeftSide(encodedFile3);
        comparisonRepository.save(file);

        mvc.perform(get(JSON_FILE_BASE_PATH + "/-50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(DIFFERENT_SIZE));
    }

    @Test
    public void should_return_different_offset() throws Exception {
        Comparison file = new Comparison(-50l);
        file.setEncodedRightSide(encodedFile1);
        file.setEncodedLeftSide(encodedFile2);
        comparisonRepository.save(file);

        mvc.perform(get(JSON_FILE_BASE_PATH + "/-50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(DIFFERENT_OFFSET));
    }

    @Test
    public void should_return_comparison_not_found_exception() throws Exception {
       mvc.perform(get(JSON_FILE_BASE_PATH + "/-100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ComparisonNotFoundException));
    }

    @Test
    public void should_return_one_file_is_empty_exception() throws Exception {
        Comparison file = new Comparison(-101l);
        file.setEncodedRightSide(encodedFile1);
        comparisonRepository.save(file);

        mvc.perform(get(JSON_FILE_BASE_PATH + "/-101")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof OneFileIsEmptyException));
    }

    @Test
    public void error_left_file_null() throws Exception {
        mvc.perform(post(JSON_FILE_BASE_PATH + JSON_LEFT_FILE_PATH.replace("{id}","1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson("")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void error_right_file_null() throws Exception {
        mvc.perform(post(JSON_FILE_BASE_PATH + JSON_RIGHT_FILE_PATH.replace("{id}","1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson("")))
                .andExpect(status().isBadRequest());
    }

}
