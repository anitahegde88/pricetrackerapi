package org.example.pricetracker_assignement.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@NoArgsConstructor
public class PriceTrackerDTO implements Serializable {
  @NonNull @NotEmpty private String productUrl;
  private double desiredPrice;
  @NonNull
  @NotEmpty
  private String frequency;

  public @NonNull String getProductUrl() {
    return productUrl;
  }

  public void setProductUrl(@NonNull String productUrl) {
    this.productUrl = productUrl;
  }

  public double getDesiredPrice() {
    return desiredPrice;
  }

  public void setDesiredPrice(double desiredPrice) {
    this.desiredPrice = desiredPrice;
  }

  public String getFrequency() {
    return frequency;
  }

  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }
}
