package org.example.pricetracker_assignement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalTime;

@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PRODUCT_URL")
    private String productUrl;

    @Column(name = "DESIRED_PRICE")
    private double desiredPrice;

    @Column(name = "FREQUENCY")
    private Long frequency;

    @Column(name = "LAST_RUN_TIME")
    private LocalTime lastRunTime;


    public Users() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public double getDesiredPrice() {
        return desiredPrice;
    }

    public void setDesiredPrice(double desiredPrice) {
        this.desiredPrice = desiredPrice;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    public LocalTime getLastRunTime() {
        return lastRunTime;
    }

    public void setLastRunTime(LocalTime lastRunTime) {
        this.lastRunTime = lastRunTime;
    }
}
