package org.example.pricetracker_assignement.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.example.pricetracker_assignement.entities.Users;
import org.example.pricetracker_assignement.model.JsonFileReader;
import org.example.pricetracker_assignement.repository.UsersRepository;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class PriceTrackerScheduler {

  private final TaskScheduler taskScheduler;
  private final UsersRepository usersRepository;
  private final JsonFileReader jsonFileReader;

  public PriceTrackerScheduler(UsersRepository usersRepository, JsonFileReader jsonFileReader) {
    this.usersRepository = usersRepository;
    this.jsonFileReader = jsonFileReader;
    ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
    scheduler.setPoolSize(2);
    scheduler.initialize();
    this.taskScheduler = scheduler;
  }

  @Scheduled(fixedDelay = 60 * 1000)
  public void globalPoolling() throws FileNotFoundException {
    double actualPrice = jsonFileReader.readFromJsonFile();
    synchronized (this) {
      List<Users> userList = usersRepository.findUsersWithElapsedSchedule();
      if (!userList.isEmpty()) {

        userList.stream()
            .forEach(
                userItem -> {
                  taskScheduler.schedule(
                      () -> {
                        if (actualPrice <= userItem.getDesiredPrice()) {
                            System.out.println("Sending notification email: Price has dropped for user");
                        }
                      },
                      Instant.now());

                  Optional<Users> user = usersRepository.findById(userItem.getUserName());
                  user.get().setLastRunTime(LocalTime.now());
                  usersRepository.saveAndFlush(user.get());
                });
      }
      userList.clear();
    }
  }
}
