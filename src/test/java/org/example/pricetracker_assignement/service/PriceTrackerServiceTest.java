package org.example.pricetracker_assignement.service;

import static org.mockito.Mockito.*;

import org.example.pricetracker_assignement.dto.PriceTrackerDTO;
import org.example.pricetracker_assignement.entities.Users;
import org.example.pricetracker_assignement.repository.PriceTrackerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PriceTrackerServiceTest {

    @Mock
    private PriceTrackerRepository priceTrackerRepository;

    @InjectMocks
    private PriceTrackerService priceTrackerService;

    private static final String USER_NAME = "nhsuser";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private PriceTrackerDTO getPriceTrackerDTO(){
        PriceTrackerDTO priceTrackerDTO = new PriceTrackerDTO();
        priceTrackerDTO.setDesiredPrice(10.00);
        priceTrackerDTO.setFrequency("1m");
        priceTrackerDTO.setProductUrl("nhs/product");
        return priceTrackerDTO;
    }

    @Test
    @DisplayName(value="Given a new user sends the request to hit the end point /product-price-tracker/scheduler" +
            "with valid values" +
            "When trackPriceScheduler() method is called" +
            "Then all user sent info over the request must be saved onto database")
    void add_newuser_to_user_table(){
        PriceTrackerDTO priceTrackerDto = getPriceTrackerDTO();
        Users users = new Users();
        users.setUserName(USER_NAME);
        users.setProductUrl(priceTrackerDto.getProductUrl());
        users.setDesiredPrice(priceTrackerDto.getDesiredPrice());
        users.setFrequency((60000L));
        priceTrackerService.trackPriceScheduler(priceTrackerDto, USER_NAME);
        verify(priceTrackerRepository, times(1)).save(users);
    }
    }
