package org.example.pricetracker_assignement.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.pricetracker_assignement.dto.PriceTrackerDTO;
import org.example.pricetracker_assignement.entities.Users;
import org.example.pricetracker_assignement.repository.UsersRepository;
import org.example.pricetracker_assignement.utilities.FrequencyParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class PriceTrackerService {

    private final UsersRepository usersRepository;
    private final FrequencyParser frequencyParser;
    public PriceTrackerService(FrequencyParser frequencyParser,
                               UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.frequencyParser = frequencyParser;
    }

    @Transactional
    public void trackPriceScheduler(@Valid PriceTrackerDTO priceTrackerDTO,
                                    @NotBlank String userName) {

        long interval = frequencyParser.parseFrequency(priceTrackerDTO.getFrequency());
        Optional<Users> user = usersRepository.findById(userName);
        if(user.isEmpty()) {
            Users usersEntity = new Users();
            usersEntity.setDesiredPrice(priceTrackerDTO.getDesiredPrice());
            usersEntity.setFrequency(interval);
            usersEntity.setProductUrl(priceTrackerDTO.getProductUrl());
            usersEntity.setUserName(userName);
            usersEntity.setLastRunTime(LocalTime.now());
            usersRepository.saveAndFlush(usersEntity);
       }

        if(user.isPresent()){
            user.get().setDesiredPrice(priceTrackerDTO.getDesiredPrice());
            user.get().setFrequency(interval);
            user.get().setProductUrl(priceTrackerDTO.getProductUrl());
            usersRepository.saveAndFlush(user.get());
        }
    }
}
