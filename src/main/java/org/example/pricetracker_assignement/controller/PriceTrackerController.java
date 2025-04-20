package org.example.pricetracker_assignement.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.pricetracker_assignement.dto.PriceTrackerDTO;
import org.example.pricetracker_assignement.service.PriceTrackerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "product-price-tracker")
public class PriceTrackerController {
  private final PriceTrackerService priceTrackerService;

    public PriceTrackerController(PriceTrackerService priceTrackerService) {
        this.priceTrackerService = priceTrackerService;
    }

    @PostMapping(value = "/scheduler", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> sendPriceTrackerNotificationTo(
      @RequestHeader @NotBlank String userName,
      @Valid @RequestBody PriceTrackerDTO priceTrackerDTO) {

    priceTrackerService.trackPriceScheduler(priceTrackerDTO, userName);
    return ResponseEntity.status(HttpStatus.OK).body("You will receive email at provided frequency");
  }
}
