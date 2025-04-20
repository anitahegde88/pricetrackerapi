package org.example.pricetracker_assignement.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.pricetracker_assignement.dto.PriceTrackerDTO;
import org.example.pricetracker_assignement.service.PriceTrackerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PriceTrackerController.class)
class PriceTrackerControllerTest {

  private static final String USER_NAME = "nhsuser";
  private static final String FIELD_USER_NAME = "userName";
  private static final String FIELD_FREQUENCY = "frequency";
  private static final String FIELD_DESIRED_PRICE = "desiredPrice";
  private static final String FIELD_PRODUCT_URL = "productUrl";

  @MockitoBean public PriceTrackerService priceTrackerService;

  private String requestBody = null;

  @Autowired private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
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
          "Given I trigger endpoint /product-price-tracker/scheduler"
              + "AND that end point should consume PriceTrackerDTO(as a body) and user name (as a header info)"
              + "When I send the request to trigger this end point with valid inputs"
              + "Then It should execute the end point successfully"
              + "AND should send the response back with correct http status code i.e. 200 OK")
  void sendPriceTrackerNotification_withValidInputs() throws Exception {

    mockMvc
        .perform(
            post("/product-price-tracker/scheduler")
                .contentType(MediaType.APPLICATION_JSON)
                .header(FIELD_USER_NAME, USER_NAME)
                .content(requestBody))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName(
      value =
          "Given I trigger endpoint /product-price-tracker/scheduler"
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
          "Given I trigger endpoint /product-price-tracker/scheduler"
              + "AND that end point should consume PriceTrackerDTO(as a body) and user name (as a header info)"
              + "When I send the request to trigger this end point with empty ProdctURL"
              + "Then It should not execute the end point"
              + "AND should send the response back with correct http status code i.e. 400 BAD_REQUEST")
  void sendPriceTrackerNotification_withProdctURLEmpty() throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode requestBodyJsonObject = (ObjectNode) objectMapper.readTree(requestBody);
    requestBodyJsonObject.put(FIELD_PRODUCT_URL, "");
    requestBody = requestBodyJsonObject.toString();
    mockMvc
        .perform(
            post("/product-price-tracker/scheduler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .header(FIELD_USER_NAME, USER_NAME))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName(
      value =
          "Given I trigger endpoint /product-price-tracker/scheduler"
              + "AND that end point should consume PriceTrackerDTO(as a body) and user name (as a header info)"
              + "When I send the request to trigger this end point with empty frequency value"
              + "Then It should not execute the end point"
              + "AND should send the response back with correct http status code i.e. 400 BAD_REQUEST")
  void sendPriceTrackerNotification_withFrequencyEmpty() throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode requestBodyJsonObject = (ObjectNode) objectMapper.readTree(requestBody);
    requestBodyJsonObject.put(FIELD_FREQUENCY, "");
    requestBody = requestBodyJsonObject.toString();
    mockMvc
        .perform(
            post("/product-price-tracker/scheduler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .header(FIELD_USER_NAME, USER_NAME))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName(
      value =
          "Given I trigger endpoint /product-price-tracker/scheduler"
              + "AND that end point should consume PriceTrackerDTO(as a body) and user name (as a header info)"
              + "When I send the request to trigger this end point with invalid desiredPrice"
              + "Then It should execute the end point"
              + "AND should send the response back with correct http status code i.e. 200 OK")
  void sendPriceTrackerNotification_withDesiredPriceEmpty() throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode requestBodyJsonObject = (ObjectNode) objectMapper.readTree(requestBody);
    requestBodyJsonObject.put(FIELD_DESIRED_PRICE, "");
    requestBody = requestBodyJsonObject.toString();
    mockMvc
        .perform(
            post("/product-price-tracker/scheduler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .header(FIELD_USER_NAME, USER_NAME))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName(
      value =
          "Given I trigger endpoint /product-price-tracker/scheduler"
              + "AND that end point consumes PriceTrackerDTO(as a body) and user name (as a header info)"
              + "When I send the request to trigger this end point with valid inputs"
              + "Then It should call service layer method to save user details"
              + "AND should send the response back with correct http status code i.e. 200 OK")
  void sendPriceTrackerNotification_withValidInputs_callsServiceLayer() throws Exception {

    mockMvc
        .perform(
            post("/product-price-tracker/scheduler")
                .contentType(MediaType.APPLICATION_JSON)
                .header(FIELD_USER_NAME, USER_NAME)
                .content(requestBody))
        .andExpect(status().isOk());
    ArgumentCaptor<PriceTrackerDTO> captor = ArgumentCaptor.forClass(PriceTrackerDTO.class);
    verify(priceTrackerService, times(1)).saveUserDetails(captor.capture(), eq(USER_NAME));
  }
}
