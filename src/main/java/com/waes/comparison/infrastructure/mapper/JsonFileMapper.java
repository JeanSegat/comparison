package com.waes.comparison.infrastructure.mapper;

import com.waes.comparison.core.domain.Position;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileDTO;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileRequestDTO;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileResponseDTO;

public class JsonFileMapper {

    public static JsonFileDTO fillJsonFileDTO(Long id, JsonFileRequestDTO requestDto, Position position) {
        JsonFileDTO dto  = new JsonFileDTO(id);
        if (position.equals(Position.LEFT))
            dto.setEncodedLeftSide(requestDto.getEncodedJsonFile());
        else
            dto.setEncodedRightSide(requestDto.getEncodedJsonFile());
        return dto;
    }

    public static JsonFileResponseDTO fillJsonFileResponse(String response) {
        return new JsonFileResponseDTO(response);
    }
}
