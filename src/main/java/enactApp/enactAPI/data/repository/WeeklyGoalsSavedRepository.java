package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.WeeklyGoalsSaved;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Saved Weekly Goals
 */
public interface WeeklyGoalsSavedRepository extends JpaRepository<WeeklyGoalsSaved, Long> {

    List<WeeklyGoalsSaved> findAll();

}
