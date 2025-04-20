package org.example.pricetracker_assignement.repository;

import org.example.pricetracker_assignement.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

  @Query(
      value =
          "SELECT * FROM Users a "
              + "WHERE DATEADD(MILLISECOND, a.FREQUENCY, a.LAST_RUN_TIME) <= CURRENT_TIMESTAMP",
      nativeQuery = true)
  List<Users> findUsersWithElapsedSchedule();
}
