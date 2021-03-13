package com.waes.comparison.core.repositories;

import com.waes.comparison.core.entities.Comparison;
import com.waes.comparison.core.exception.FileNotFoundException;
import com.waes.comparison.infrastructure.entrypoint.dto.JsonFileDTO;
import org.springframework.stereotype.Component;

@Component
public class ComparisonCustomRepositoryImpl implements ComparisonCustomRepository {
    private final ComparisonRepository comparisonRepository;

    public ComparisonCustomRepositoryImpl(final ComparisonRepository comparisonRepository) {
        this.comparisonRepository = comparisonRepository;
    }

    /**
     * Create or Update file in database.
     * @param jsonFileDTO Object to be create or update.
     */
    @Override
    public void createOrUpdate(JsonFileDTO jsonFileDTO) {
        Comparison comparisonToSave;

        try {
            comparisonToSave = findById(jsonFileDTO.getId());
        } catch (RuntimeException e){
            comparisonToSave = new Comparison(jsonFileDTO.getId());
        }

        merge(jsonFileDTO, comparisonToSave);
        comparisonRepository.save(comparisonToSave);
    }

    /**
     * Find a
     * @param id
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public Comparison findById(final Long id) throws FileNotFoundException {
        return comparisonRepository.findById(id).orElseThrow( () -> new FileNotFoundException("File not found"));
    }

    private void merge(final JsonFileDTO dto, Comparison entity) {
        if (dto.getEncodedLeftSide() != null)
            entity.setEncodedLeftSide(dto.getEncodedLeftSide());
        if (dto.getEncodedRightSide() != null)
            entity.setEncodedRightSide(dto.getEncodedRightSide());
    }
}
