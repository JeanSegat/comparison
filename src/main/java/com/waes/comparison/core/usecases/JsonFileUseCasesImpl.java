package com.waes.comparison.core.usecases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waes.comparison.core.domain.Status;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class JsonFileUseCasesImpl implements JsonFileUseCases {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Status getDiffStatus(final String encodedLeftFile, final String encodedRightFile) {
        String rightFile = getFileAsString(encodedRightFile);
        String leftFile = getFileAsString(encodedLeftFile);

        if (isSameFile(rightFile, leftFile))
            return Status.SAME_FILE;

        if (!isSameSize(rightFile, leftFile))
            return Status.DIFFERENT_SIZE;

        return Status.SAME_SIZE_DIFFERENT_FILE;

    }

    @Override
    public String getDifferentOffset(final String encodedLeftFile, final String encodedRightFile) {
        byte[] rightFile = getFileAsString(encodedRightFile).getBytes(StandardCharsets.UTF_8);
        byte[] leftFile = getFileAsString(encodedLeftFile).getBytes(StandardCharsets.UTF_8);
        StringBuilder result = new StringBuilder();

        for (int i=0; i < leftFile.length; i++) {
            if(leftFile[i] != rightFile[i]) {
                result.append(String.format("%d ", i));
            }
        }
        return result.toString().trim();
    }

    private String getFileAsString(final String file) {
        return new String(Base64.getDecoder().decode(file));
    }

    private boolean isSameFile(final String leftFile, final String rightFile) {
        return leftFile.equals(rightFile);
    }

    private boolean isSameSize(final String leftFile, final String rightFile) {
        return leftFile.length() == rightFile.length();
    }
}
