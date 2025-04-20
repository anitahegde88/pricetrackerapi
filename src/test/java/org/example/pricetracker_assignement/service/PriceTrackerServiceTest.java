package org.example.pricetracker_assignement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.example.pricetracker_assignement.dto.PriceTrackerDTO;
import org.example.pricetracker_assignement.entities.Users;
import org.example.pricetracker_assignement.repository.UsersRepository;
import org.example.pricetracker_assignement.utilities.FrequencyParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PriceTrackerServiceTest {

  private static final String USER_NAME = "nhsuser";
  private static final String PRODUCT_URL_VALUE="abc/nhsproduct";
  private static final double DESIRED_PRICE_VALUE=10.00;
  private static final String FREQUENCY_VALUE="1m";
  @Mock private UsersRepository usersRepository;
  @Mock private FrequencyParser frequencyParser;
  @InjectMocks private PriceTrackerService priceTrackerService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  private PriceTrackerDTO getPriceTrackerDTO() {
    PriceTrackerDTO priceTrackerDTO = new PriceTrackerDTO();
    priceTrackerDTO.setDesiredPrice(DESIRED_PRICE_VALUE);
    priceTrackerDTO.setFrequency(FREQUENCY_VALUE);
    priceTrackerDTO.setProductUrl(PRODUCT_URL_VALUE);
    return priceTrackerDTO;
  }

  @Test
  @DisplayName(
      value =
          "Given a new user sends the request to hit the end point /product-price-tracker/scheduler"
              + "with valid values"
              + "When trackPriceScheduler() method is called"
              + "Then all user sent info over the request must be saved onto database")
  void add_newuser_to_user_table() {
    PriceTrackerDTO priceTrackerDto = getPriceTrackerDTO();
    when(usersRepository.findById(any())).thenReturn(Optional.empty());
    when(frequencyParser.parseFrequency(any())).thenReturn(60000L);
    priceTrackerService.saveUserDetails(priceTrackerDto, USER_NAME);
    ArgumentCaptor<Users> captor = ArgumentCaptor.forClass(Users.class);
    verify(usersRepository, times(1)).saveAndFlush(captor.capture());
    Users capturedUser = captor.getValue();
    assertEquals(USER_NAME, capturedUser.getUserName());
    assertEquals(priceTrackerDto.getProductUrl(), capturedUser.getProductUrl());
    assertEquals(priceTrackerDto.getDesiredPrice(), capturedUser.getDesiredPrice());
    assertEquals(60000L, capturedUser.getFrequency());
  }

  @Test
  @DisplayName(
      value =
          "Given a new user sends the request to hit the end point /product-price-tracker/scheduler"
              + "with valid values"
              + "And user already exists in the DB"
              + "When trackPriceScheduler() method is called"
              + "Then all user sent info over the request must be updated onto database")
  void add_existing_user_details_to_table() {
    PriceTrackerDTO priceTrackerDto = getPriceTrackerDTO();
    when(usersRepository.findById(any())).thenReturn(Optional.of(new Users()));
    when(frequencyParser.parseFrequency(any())).thenReturn(60000L);
    priceTrackerService.saveUserDetails(priceTrackerDto, USER_NAME);
    ArgumentCaptor<Users> captor = ArgumentCaptor.forClass(Users.class);
    verify(usersRepository, times(1)).saveAndFlush(captor.capture());
  }
}
