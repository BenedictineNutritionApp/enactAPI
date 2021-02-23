package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.CancerTreatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for CancerTreatment
 */
public interface CancerTreatmentRepository extends JpaRepository<CancerTreatment, Long> {

    Optional<CancerTreatment> findCancerTreatmentByUserId(Long id);


}
