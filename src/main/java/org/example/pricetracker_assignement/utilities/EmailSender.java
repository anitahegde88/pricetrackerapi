package org.example.pricetracker_assignement.utilities;

import org.example.pricetracker_assignement.entities.Users;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class EmailSender {

    public void sendEmail(double actualPrice, Users userItem){
        System.out.println("Sending notification email: Price has dropped, current price of the product " + userItem.getProductUrl() + " is  " + actualPrice + " " + "user -" + userItem.getUserName()  + "  " + Instant.now());
    }
}
