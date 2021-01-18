package ru.skubatko.dev.otus.java.hw23.processor;

import java.io.IOException;
import java.util.Map;

public interface TemplateProcessor {

    String getPage(String filename, Map<String, Object> data) throws IOException;
}
