package org.example.pricetracker_assignement.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceTrackerDTOTest {

  @Test
  @DisplayName(
      value =
          "Given I have a PriceTracker instance,"
              + "When I try to set/get the values of user name, product URL, desired price in it"
              + "Then I should be able set/get those values correctly")
  void checkGetterMethodsOfPriceTracker() {

    PriceTrackerDTO priceTrackerDTO = new PriceTrackerDTO();
    priceTrackerDTO.setUserName("nhsuser");
    priceTrackerDTO.setProductUrl("abc/nhsproduct");
    priceTrackerDTO.setDesiredPrice(0.0);
    priceTrackerDTO.setFrequency("1m");

    assertEquals("nhsuser", priceTrackerDTO.getUserName());
    assertEquals("abc/nhsproduct", priceTrackerDTO.getProductUrl());
    assertEquals(0.0, priceTrackerDTO.getDesiredPrice());
    assertEquals("1m", priceTrackerDTO.getFrequency());
  }
}
