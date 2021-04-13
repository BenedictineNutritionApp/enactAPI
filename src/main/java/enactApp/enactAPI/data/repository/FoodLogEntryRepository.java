package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.FoodLogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Repository for DailyFoodLogs
 */
public interface FoodLogEntryRepository extends JpaRepository<FoodLogEntry, Long> {

    List<FoodLogEntry> findFoodLogEntryByUserIdAndDateOrderByEntryTime(Long userId, Date date);

    @Query(value = "SELECT *, COUNT(`food_id`) AS `value_occurrence` FROM `food_log_entry` WHERE `user_id` = ?1 AND `date` BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE() GROUP BY `food_id` ORDER BY `value_occurrence` DESC LIMIT 10 ", nativeQuery = true)
    List<FoodLogEntry> findFrequentFoods(Long userId);

    

    Optional<FoodLogEntry> findFoodLogEntryById(Long id);

    void deleteFoodLogEntryById(Long id);


}
