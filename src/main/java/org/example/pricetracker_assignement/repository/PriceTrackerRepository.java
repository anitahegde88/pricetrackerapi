package org.example.pricetracker_assignement.repository;

import org.example.pricetracker_assignement.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceTrackerRepository extends JpaRepository<Users, String> {

}
