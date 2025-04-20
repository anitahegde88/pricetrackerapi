package org.example.pricetracker_assignement.model;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

import org.example.pricetracker_assignement.utilities.JsonFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JsonFileReaderTest {

  private static final String JSON_FILE_NAME = "jsonfiles/actual_price.json";
  private static final String ACTUAL_PRICE = "actualPrcie";


  private JsonFileReader jsonFileReader;

  @BeforeEach
  void setUp() {
    jsonFileReader = new JsonFileReader();
  }

  @Test
  @DisplayName(
      "Given a valid JSON file, "
          + "When readFromJsonFile is called, "
          + "Then it should return the correct price")
  void testReadFromJsonFile_ValidFile() throws Exception {

    String fileName = JSON_FILE_NAME;
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource(fileName).getFile());
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(file);
    double expectedPrice = jsonNode.get(ACTUAL_PRICE).asDouble();

    double actualPrice = jsonFileReader.readFromJsonFile();

    assertEquals(
        expectedPrice,
        actualPrice,
        "The price read from the JSON file should match the expected value.");
  }
}
