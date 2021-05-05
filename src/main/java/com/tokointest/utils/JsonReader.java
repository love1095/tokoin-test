package com.tokointest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tokointest.models.BaseEntity;

@Component
@RequiredArgsConstructor
public class JsonReader {

  private final ObjectMapper objectMapper;

  public <T extends BaseEntity> List<T> readFromFile(final String filePath,
                                                     final TypeReference<List<T>> reference) throws IOException {
    try (InputStream inputStream = JsonReader.class.getResourceAsStream(filePath)) {
      return objectMapper.readValue(inputStream, reference);
    }
  }
}
