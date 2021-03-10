package com.waes.comparison.core.usecases;

import com.waes.comparison.core.domain.Status;

public interface JsonFileUseCases {
    Status getDiffStatus(String encodedLeftFile, String encodedRightFile);

    String getDifferentOffset(String encodedLeftFile, String encodedRightFile);
}
