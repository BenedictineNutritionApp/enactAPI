package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.ActivityOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for ActivityOption
 */
public interface ActivityOptionRepository extends JpaRepository<ActivityOption, Long> {

    Optional<ActivityOption> findActivityOptionById(Long id);
    Optional<ActivityOption> findActivityOptionByType(String type);
    List<ActivityOption> findAll();



}
