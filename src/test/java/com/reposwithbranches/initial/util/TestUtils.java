package com.reposwithbranches.initial.util;

import lombok.SneakyThrows;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.nio.charset.StandardCharsets;

public class TestUtils {
    private static final ResourceLoader resourceLoader = new DefaultResourceLoader();

    @SneakyThrows
    public static String readResource(String resource) {
        return resourceLoader.getResource("classpath:" + resource).getContentAsString(StandardCharsets.UTF_8);
    }

}
