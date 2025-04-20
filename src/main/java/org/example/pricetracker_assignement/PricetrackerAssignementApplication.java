package org.example.pricetracker_assignement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "org.example.pricetracker_assignement.repository")
@EntityScan(basePackages = {"org.example.pricetracker_assignement.entities"})
@EnableScheduling
public class PricetrackerAssignementApplication {

  public static void main(String[] args) {
    SpringApplication.run(PricetrackerAssignementApplication.class, args);
  }
}
