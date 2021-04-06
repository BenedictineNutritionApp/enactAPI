package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.FoodLogEntry;
import enactApp.enactAPI.data.model.WeeklyGoalsSaved;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Saved Weekly Goals
 */
public interface WeeklyGoalsSavedRepository extends JpaRepository<WeeklyGoalsSaved, Long> {

    List<WeeklyGoalsSaved> findAll();
}
