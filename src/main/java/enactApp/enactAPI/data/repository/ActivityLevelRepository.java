package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.ActivityLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for ActivityLevel
 */
public interface ActivityLevelRepository extends JpaRepository<ActivityLevel, Long> {

    Optional<ActivityLevel> findActivityLevelById(Long id);
    Optional<ActivityLevel> findActivityLevelByLevel(String level);


}
