package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.CommonPortionSizeDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Common portion size descriptions
 */
public interface CommonPortionSizeDescriptionRepository extends JpaRepository<CommonPortionSizeDescription, Long> {

    Optional<CommonPortionSizeDescription> findCommonPortionSizeDescriptionByDescription(String description);
    Optional<CommonPortionSizeDescription> findCommonPortionSizeDescriptionById(Long id);


}
