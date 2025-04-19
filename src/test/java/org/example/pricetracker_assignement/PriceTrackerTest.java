package org.example.pricetracker_assignement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTrackerTest {

    @Test
    @DisplayName(value="Given I have a PriceTracker instance," +
            "When I try to set/get the values of user name, product URL, desired price" +
            "Then I should be able set/get those values correctly")
    void checkGetterMethodsOfPriceTracker(){

        PriceTracker priceTracker = new PriceTracker();
        priceTracker.setUserName("nhsuser");
        priceTracker.setProductUrl("abc/nhsproduct");
        priceTracker.setDesiredPrice("0.0");
        priceTracker.setFrequency("1m");

        assertEquals("nhsuser", priceTracker.getUserName());
        assertEquals("nhsuser", priceTracker.getProductUrl());
        assertEquals("nhsuser", priceTracker.getDesiredPrice());
        assertEquals("nhsuser", priceTracker.getFrequency());
    }
}