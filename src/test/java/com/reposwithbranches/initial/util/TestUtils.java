package com.reposwithbranches.initial.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.nio.charset.StandardCharsets;

public class TestUtils {
    private static final ObjectMapper mapper = getObjectMapper();
    private static final ResourceLoader resourceLoader = new DefaultResourceLoader();

    @SneakyThrows
    public static String readResource(String resource) {
        return resourceLoader.getResource("classpath:" + resource).getContentAsString(StandardCharsets.UTF_8);
    }

    public static ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
