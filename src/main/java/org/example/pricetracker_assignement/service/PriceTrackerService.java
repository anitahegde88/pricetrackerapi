package org.example.pricetracker_assignement.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.pricetracker_assignement.dto.PriceTrackerDTO;
import org.example.pricetracker_assignement.entities.Users;
import org.example.pricetracker_assignement.model.UsersTableUtils;
import org.example.pricetracker_assignement.repository.UsersRepository;
import org.example.pricetracker_assignement.utilities.FrequencyParser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceTrackerService {

    private final UsersRepository usersRepository;
    private final UsersTableUtils usersTableUtils;
    private final FrequencyParser frequencyParser;
    public PriceTrackerService(FrequencyParser frequencyParser,
                               UsersRepository usersRepository,
                               UsersTableUtils usersTableUtils) {
        this.usersRepository = usersRepository;
        this.usersTableUtils = usersTableUtils;
        this.frequencyParser = frequencyParser;
    }

    public void trackPriceScheduler(@Valid PriceTrackerDTO priceTrackerDTO,
                                    @NotBlank String userName) {

        long interval = frequencyParser.parseFrequency(priceTrackerDTO.getFrequency());
        Optional<Users> user = usersRepository.findById(userName);
       if(!user.isPresent()){
           usersTableUtils.saveNewUserDetailsToUsersTable(userName,
                   priceTrackerDTO.getProductUrl(),
                   priceTrackerDTO.getDesiredPrice(),
                   interval);
       }

    }
}
