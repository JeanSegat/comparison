package com.waes.comparison.infrastructure.entrypoint;

import com.waes.comparison.core.domain.Position;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileDTO;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileRequestDTO;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileResponseDTO;
import com.waes.comparison.infrastructure.facade.JsonFileFacade;
import com.waes.comparison.infrastructure.mapper.JsonFileMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/diff/")
public class JsonFileController {

    private final JsonFileFacade jsonFileFacade;

    private JsonFileController(final JsonFileFacade jsonFileFacade) {
        this.jsonFileFacade = jsonFileFacade;
    }

    @PostMapping("/{id}/left")
    @ResponseStatus(HttpStatus.CREATED)
    public void createLeftFile(@PathVariable Long id, @RequestBody JsonFileRequestDTO requestDTO) {
        JsonFileDTO dto = JsonFileMapper.fillJsonFileDTO(id, requestDTO, Position.LEFT);
        jsonFileFacade.createOrUpdate(dto);
    }

    @PostMapping("/{id}/right")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRightFile(@PathVariable Long id, @RequestBody JsonFileRequestDTO requestDTO) {
        JsonFileDTO dto = JsonFileMapper.fillJsonFileDTO(id, requestDTO, Position.RIGHT);
        jsonFileFacade.createOrUpdate(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public JsonFileResponseDTO getDifferenceStatus(@PathVariable Long id) {
        return JsonFileMapper.fillJsonFileResponse(jsonFileFacade.getDifferenceStatus(id));
    }
}
