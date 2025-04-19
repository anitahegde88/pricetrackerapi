package org.example.pricetracker_assignement.utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FrequencyParserTest {

  private final FrequencyParser frequencyParser = new FrequencyParser();

  @Test
  @DisplayName(value = "")
  void parseValidFrequency() {
    Long parsedFrequencyInMilliseconds = frequencyParser.parseFrequency("1m");
    assertEquals( 60000L, parsedFrequencyInMilliseconds);
    parsedFrequencyInMilliseconds = frequencyParser.parseFrequency("2m");
    assertEquals( 120000L, parsedFrequencyInMilliseconds);
  }
}
