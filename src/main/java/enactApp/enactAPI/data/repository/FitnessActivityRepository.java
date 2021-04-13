package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.FitnessActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FitnessActivityRepository extends JpaRepository<FitnessActivity, Long> {

    List<FitnessActivity> findAll();

    List<FitnessActivity> findAllByUserId(long userId);

    List<FitnessActivity> findFitnessActivitiesByDateTimeAfterAndAndIntensityIsGreaterThanAndUserId(LocalDateTime date, String intensity, long userId);

    List<FitnessActivity> findFitnessActivitiesByDateTimeAfterAndUserId(LocalDateTime date, long userId);

//Option<FitnessActivity> findFitnessActivitiesByType

}
