package com.waes.comparison.core.usecases;

import com.waes.comparison.core.domain.Status;
import com.waes.comparison.core.exception.OneFileIsEmptyException;

public interface JsonFileUseCases {
    Status getDiffStatus(String encodedLeftFile, String encodedRightFile) throws OneFileIsEmptyException;

    String getDifferentOffset(String encodedLeftFile, String encodedRightFile);

}
