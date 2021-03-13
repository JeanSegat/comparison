package com.waes.comparison.infrastructure.facade;

import com.waes.comparison.core.domain.Status;
import com.waes.comparison.core.entities.Comparison;
import com.waes.comparison.core.exception.ComparisonNotFoundException;
import com.waes.comparison.core.exception.OneFileIsEmptyException;
import com.waes.comparison.core.repositories.ComparisonCustomRepository;
import com.waes.comparison.core.usecases.ComparisonUseCases;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileDTO;
import org.springframework.stereotype.Component;

@Component
public class JsonFileFacadeImpl implements JsonFileFacade {

    private final ComparisonCustomRepository comparisonCustomRepository;

    private final ComparisonUseCases comparisonUseCases;

    private static final String SAME_FILE = "Same File";
    private static final String DIFFERENT_SIZE = "Different Size";

    public JsonFileFacadeImpl(final ComparisonCustomRepository comparisonCustomRepository, ComparisonUseCases comparisonUseCases) {
        this.comparisonCustomRepository = comparisonCustomRepository;
        this.comparisonUseCases = comparisonUseCases;
    }


    @Override
    public void createOrUpdate(JsonFileDTO dto) {
        comparisonCustomRepository.createOrUpdate(dto);
    }

    @Override
    public String getDifferenceStatus(Long id) throws ComparisonNotFoundException, OneFileIsEmptyException {
        Comparison comparison = comparisonCustomRepository.findById(id);
        Status status = comparisonUseCases.getDiffStatus(comparison.getEncodedLeftSide(), comparison.getEncodedRightSide());
        String result;

        switch (status) {
            case SAME_FILE: return SAME_FILE;
            case DIFFERENT_SIZE: return DIFFERENT_SIZE;
            default: return comparisonUseCases.getDifferentOffset(comparison.getEncodedLeftSide(), comparison.getEncodedRightSide());
        }
    }
}
