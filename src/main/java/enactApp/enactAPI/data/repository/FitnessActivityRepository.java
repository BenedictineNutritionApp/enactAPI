package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.FitnessActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FitnessActivityRepository extends JpaRepository<FitnessActivity, Long> {

    List<FitnessActivity> findAll();

//Option<FitnessActivity> findFitnessActivitiesByType

}
