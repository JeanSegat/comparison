package com.waes.comparison.utils;

import java.util.Base64;

public class JsonFileUtils {

    public static String getEncodedFile1() {
        String file1 = "{\"name\":\"john\",\"age\":22,\"city\":\"Joinville\",\"country\":\"Brazil\"}";
        return Base64.getEncoder().encodeToString(file1.getBytes());
    }

    public static String getEncodedFile2() {
        String file2 = "{\"name\":\"jean\",\"age\":29,\"city\":\"Joinville\",\"country\":\"Brazil\"}";
        return Base64.getEncoder().encodeToString(file2.getBytes());
    }

    public static String getEncodedFile3() {
        String file3 = "{\"name\":\"Maria\",\"age\":34,\"city\":\"Eindhoven\",\"country\":\"Netherlands\"}";
        return Base64.getEncoder().encodeToString(file3.getBytes());
    }
}
