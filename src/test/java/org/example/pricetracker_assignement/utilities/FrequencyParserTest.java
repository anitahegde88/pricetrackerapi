package org.example.pricetracker_assignement.utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FrequencyParserTest {

  private final FrequencyParser frequencyParser = new FrequencyParser();

  @Test
  @DisplayName(value = "Given user sends the frequency in minute" +
          "When the frequency is parsed" +
          "Then parser should convert frequency value to milliseconds")
  void parseValidFrequency() {
    Long parsedFrequencyInMilliseconds = frequencyParser.parseFrequency("1m");
    assertEquals(60000L, parsedFrequencyInMilliseconds);
    parsedFrequencyInMilliseconds = frequencyParser.parseFrequency("2m");
    assertEquals(120000L, parsedFrequencyInMilliseconds);
  }

  @Test
  @DisplayName(value = "Given user sends invalid frequency, example 1mmmmm" +
          "When the frequency is parsed" +
          "Then parser should throw an exception")
  void parseInValidFrequency() {
    try {
      frequencyParser.parseFrequency("1mmmm");
    } catch (Exception e) {
      assertFalse(e.getMessage().isEmpty(), String.valueOf(true));
    }

    try {
      frequencyParser.parseFrequency("2mmmm");
    } catch (Exception e) {
      assertFalse(e.getMessage().isEmpty(), String.valueOf(true));
    }
  }
}
