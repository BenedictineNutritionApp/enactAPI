package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.WeeklyGoals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WeeklyGoalsRepository extends JpaRepository<WeeklyGoals, Long> {

    List<WeeklyGoals> findAll();
    Optional<WeeklyGoals> findWeeklyGoalsByGoalDescription(String description);
    Optional<WeeklyGoals> findWeeklyGoalsById(Long Id);
}
