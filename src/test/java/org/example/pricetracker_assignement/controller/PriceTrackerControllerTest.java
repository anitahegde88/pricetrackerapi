package org.example.pricetracker_assignement.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PriceTrackerController.class)
class PriceTrackerControllerTest {

  private final String requestBody =
      """
            {
                "productUrl": "http://nhs/product",
                "desiredPrice": 10.0,
                "frequency": "1m"
            }
        """;
  @Autowired private MockMvc mockMvc;

  @BeforeEach
  void setUp() {}

  @Test
  @DisplayName(
      value =
          "Given I have a controller class named PriceTrackerController"
              + "AND I want to create a postmapping end point named /product-price-tracker/scheduler"
              + "AND that end point should consume PriceTrackerDTO(as a body) and user name (as a header info)"
              + "When I send the request to trigger this end point with valid inputs"
              + "Then It should execute the end point successfully"
              + "AND should send the response back with correct http status code i.e. 200 OK")
  void sendPriceTrackerNotificationTo() throws Exception {

    mockMvc
        .perform(
            post("/product-price-tracker/scheduler")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userName", "nhsuser")
                .content(requestBody))
        .andExpect(status().isOk());
  }
}
