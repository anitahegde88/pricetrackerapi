package org.example.pricetracker_assignement.scheduler;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.pricetracker_assignement.entities.Users;
import org.example.pricetracker_assignement.repository.UsersRepository;
import org.example.pricetracker_assignement.utilities.EmailSender;
import org.example.pricetracker_assignement.utilities.JsonFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PriceTrackerSchedulerTest {
  private static final String USER_NAME = "nhsuser";
  @Mock private UsersRepository usersRepository;
  @Mock private JsonFileReader jsonFileReader;
  @Mock private EmailSender emailSender;
  @InjectMocks private PriceTrackerScheduler priceTrackerScheduler;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName(
      "Given a user stored in database is due to receive notification"
          + "When scheduler is triggered"
          + "Then user should get notification (instead of email, display message on the console")
  void globalPoling_with_one_user_due_to_get_notification() throws FileNotFoundException {

    Users user1 = new Users();
    user1.setUserName(USER_NAME);
    user1.setDesiredPrice(100.0);
    user1.setLastRunTime(null);

    List<Users> userList = new ArrayList<>();
    userList.add(user1);

    when(usersRepository.findUsersDueForNotification()).thenReturn(userList);
    when(usersRepository.findById(anyString())).thenReturn(Optional.of(user1));

    doReturn(90.0).when(jsonFileReader).readFromJsonFile();
    priceTrackerScheduler.sendPriceDropNotifications();

    verify(usersRepository, times(1)).findUsersDueForNotification();
    verify(usersRepository, times(1)).findById(USER_NAME);
  }

  @Test
  @DisplayName(
      "Given no user stored in database is due to receive notification"
          + "When scheduler is triggered"
          + "Then no user should get notification")
  void globalPoling_with_no_user_due_to_get_notification() throws FileNotFoundException {

    List<Users> userList = new ArrayList<>();

    when(usersRepository.findUsersDueForNotification()).thenReturn(userList);
    doReturn(90.0).when(jsonFileReader).readFromJsonFile();

    priceTrackerScheduler.sendPriceDropNotifications();

    verify(usersRepository, times(1)).findUsersDueForNotification();
    verify(usersRepository, times(0)).findById(USER_NAME);
  }

  @Test
  @DisplayName(
      "Given a user stored in database is due to receive notification"
          + "And actual price is more than desired price"
          + "When scheduler is triggered"
          + "Then user should not get notification")
  void globalPoling_with_one_user_no_notification() throws FileNotFoundException {

    Users user1 = new Users();
    user1.setUserName(USER_NAME);
    user1.setDesiredPrice(100.0);
    user1.setLastRunTime(null);

    List<Users> userList = new ArrayList<>();
    userList.add(user1);

    when(usersRepository.findUsersDueForNotification()).thenReturn(userList);
    when(usersRepository.findById(anyString())).thenReturn(Optional.of(user1));

    doReturn(110.0).when(jsonFileReader).readFromJsonFile();
    priceTrackerScheduler.sendPriceDropNotifications();

    verify(usersRepository, times(1)).findUsersDueForNotification();
    verify(usersRepository, times(1)).findById(USER_NAME);
    verify(emailSender, times(0)).sendEmail(110.0, user1);
  }
}
