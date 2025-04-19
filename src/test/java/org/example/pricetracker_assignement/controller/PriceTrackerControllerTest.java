package org.example.pricetracker_assignement.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PriceTrackerController.class)
class PriceTrackerControllerTest {
  @Autowired private MockMvc mockMvc;
  private String requestBody = null;

  @BeforeEach
  void setUp() {

    requestBody =
        """
                  {
                      "productUrl": "http://nhs/product",
                      "desiredPrice": 10.0,
                      "frequency": "1m"
                  }
              """;
  }

  @Test
  @DisplayName(
      value =
          "Given I have a controller class named PriceTrackerController"
              + "AND I want to create an endpoint(postmapping) named /product-price-tracker/scheduler"
              + "AND that end point should consume PriceTrackerDTO(as a body) and user name (as a header info)"
              + "When I send the request to trigger this end point with valid inputs"
              + "Then It should execute the end point successfully"
              + "AND should send the response back with correct http status code i.e. 200 OK")
  void sendPriceTrackerNotification_withValidInputs() throws Exception {

    mockMvc
        .perform(
            post("/product-price-tracker/scheduler")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userName", "nhsuser")
                .content(requestBody))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName(
      value =
          "Given I have a controller class named PriceTrackerController"
              + "AND I want to create an endpoint(postmapping) named /product-price-tracker/scheduler"
              + "AND that end point should consume PriceTrackerDTO(as a body) and user name (as a header info)"
              + "When I send the request to trigger this end point with no user name"
              + "Then It should not execute the end point"
              + "AND should send the response back with correct http status code i.e. 400 BAD_REQUEST")
  void sendPriceTrackerNotification_withUserNameEmpty() throws Exception {

    mockMvc
        .perform(
            post("/product-price-tracker/scheduler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName(
      value =
          "Given I have a controller class named PriceTrackerController"
              + "AND I want to create an endpoint(postmapping) named /product-price-tracker/scheduler"
              + "AND that end point should consume PriceTrackerDTO(as a body) and user name (as a header info)"
              + "When I send the request to trigger this end point with empty ProdctURL"
              + "Then It should not execute the end point"
              + "AND should send the response back with correct http status code i.e. 400 BAD_REQUEST")
  void sendPriceTrackerNotification_withProdctURLEmpty() throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode requestBodyJsonObject = (ObjectNode) objectMapper.readTree(requestBody);
    requestBodyJsonObject.put("productUrl", "");
    requestBody = requestBodyJsonObject.toString();
    mockMvc
        .perform(
            post("/product-price-tracker/scheduler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .header("userName", "nhsuser"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName(
      value =
          "Given I have a controller class named PriceTrackerController"
              + "AND I want to create an endpoint(postmapping) named /product-price-tracker/scheduler"
              + "AND that end point should consume PriceTrackerDTO(as a body) and user name (as a header info)"
              + "When I send the request to trigger this end point with empty frequency value"
              + "Then It should not execute the end point"
              + "AND should send the response back with correct http status code i.e. 400 BAD_REQUEST")
  void sendPriceTrackerNotification_withFrequencyEmpty() throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode requestBodyJsonObject = (ObjectNode) objectMapper.readTree(requestBody);
    requestBodyJsonObject.put("frequency", "");
    requestBody = requestBodyJsonObject.toString();
    mockMvc
        .perform(
            post("/product-price-tracker/scheduler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .header("userName", "nhsuser"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName(
      value =
          "Given I have a controller class named PriceTrackerController"
              + "AND I want to create an endpoint(postmapping) named /product-price-tracker/scheduler"
              + "AND that end point should consume PriceTrackerDTO(as a body) and user name (as a header info)"
              + "When I send the request to trigger this end point with invalid desiredPrice"
              + "Then It should execute the end point"
              + "AND should send the response back with correct http status code i.e. 200 OK")
  void sendPriceTrackerNotification_withDesiredPriceEmpty() throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode requestBodyJsonObject = (ObjectNode) objectMapper.readTree(requestBody);
    requestBodyJsonObject.put("desiredPrice", "");
    requestBody = requestBodyJsonObject.toString();
    mockMvc
        .perform(
            post("/product-price-tracker/scheduler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .header("userName", "nhsuser"))
        .andExpect(status().isOk());
  }
}
