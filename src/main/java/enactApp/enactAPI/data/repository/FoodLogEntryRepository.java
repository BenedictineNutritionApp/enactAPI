package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.FoodLogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Repository for DailyFoodLogs
 */
public interface FoodLogEntryRepository extends JpaRepository<FoodLogEntry, Long> {

    List<FoodLogEntry> findFoodLogEntryByUserIdAndDateOrderByEntryTime(Long userId, Date date);
    Optional<FoodLogEntry> findFoodLogEntryById(Long id);
    void deleteFoodLogEntryById(Long id);



}
