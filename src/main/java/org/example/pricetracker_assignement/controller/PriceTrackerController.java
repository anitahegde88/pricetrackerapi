package org.example.pricetracker_assignement.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.pricetracker_assignement.dto.PriceTrackerDTO;
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

  @PostMapping(value = "/scheduler", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> sendPriceTrackerNotificationTo(
      @RequestHeader @NotBlank String userName,
      @Valid @RequestBody PriceTrackerDTO priceTrackerDTO) {
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
