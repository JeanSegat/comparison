package com.waes.comparison.infrastructure.entrypoint;

import com.waes.comparison.core.domain.Position;
import com.waes.comparison.core.exception.ComparisonNotFoundException;
import com.waes.comparison.core.exception.OneFileIsEmptyException;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileDTO;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileRequestDTO;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileResponseDTO;
import com.waes.comparison.infrastructure.facade.JsonFileFacade;
import com.waes.comparison.infrastructure.mapper.JsonFileMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("v1/diff")
public class JsonFileController {

    private final JsonFileFacade jsonFileFacade;

    private JsonFileController(final JsonFileFacade jsonFileFacade) {
        this.jsonFileFacade = jsonFileFacade;
    }

    @PostMapping("/{id}/left")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create left file for the id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Left file created"),
            @ApiResponse(code = 400, message = "Please send a file")
    })
    public void createLeftFile(@PathVariable @NotBlank Long id, @RequestBody @Valid JsonFileRequestDTO requestDTO) throws MethodArgumentNotValidException {
        JsonFileDTO dto = JsonFileMapper.fillJsonFileDTO(id, requestDTO, Position.LEFT);
        jsonFileFacade.createOrUpdate(dto);
    }

    @PostMapping("/{id}/right")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create right file for the id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Right file created"),
            @ApiResponse(code = 400, message = "Please send a file")
    })
    public void createRightFile(@PathVariable @NotBlank Long id, @RequestBody @Valid JsonFileRequestDTO requestDTO) {
        JsonFileDTO dto = JsonFileMapper.fillJsonFileDTO(id, requestDTO, Position.RIGHT);
        jsonFileFacade.createOrUpdate(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Compare two file (left file and right file) and return de difference status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comparison done"),
            @ApiResponse(code = 404, message = "File not found"),
            @ApiResponse(code = 406, message = "One file is null"),
    })
    public JsonFileResponseDTO getDifferenceStatus(@PathVariable Long id) throws ComparisonNotFoundException, OneFileIsEmptyException {
        return JsonFileMapper.fillJsonFileResponse(jsonFileFacade.getDifferenceStatus(id));
    }
}
