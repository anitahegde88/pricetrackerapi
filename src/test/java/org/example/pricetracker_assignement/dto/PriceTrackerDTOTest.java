package org.example.pricetracker_assignement.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceTrackerDTOTest {

  private static final String PRODUCT_URL_VALUE="abc/nhsproduct";
  private static final double DESIRED_PRICE_VALUE=0.0;
  private static final String FREQUENCY_VALUE="1m";

  @Test
  @DisplayName(
      value =
          "Given I have a PriceTracker instance,"
              + "When I try to set/get the values of user name, product URL, desired price in it"
              + "Then I should be able set/get those values correctly")
  void checkGetterMethodsOfPriceTracker() {

    PriceTrackerDTO priceTrackerDTO = new PriceTrackerDTO();
    priceTrackerDTO.setProductUrl(PRODUCT_URL_VALUE);
    priceTrackerDTO.setDesiredPrice(DESIRED_PRICE_VALUE);
    priceTrackerDTO.setFrequency(FREQUENCY_VALUE);

    assertEquals(PRODUCT_URL_VALUE, priceTrackerDTO.getProductUrl());
    assertEquals(DESIRED_PRICE_VALUE, priceTrackerDTO.getDesiredPrice());
    assertEquals(FREQUENCY_VALUE, priceTrackerDTO.getFrequency());
  }
}
