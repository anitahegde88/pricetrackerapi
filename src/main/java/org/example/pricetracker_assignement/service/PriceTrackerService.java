package org.example.pricetracker_assignement.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.pricetracker_assignement.dto.PriceTrackerDTO;
import org.springframework.stereotype.Service;

@Service
public class PriceTrackerService {

    public void trackPriceScheduler(@Valid PriceTrackerDTO priceTrackerDTO,
                                    @NotBlank String userName) {
    }
}
