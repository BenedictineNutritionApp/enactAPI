package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.Metric;
import enactApp.enactAPI.data.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {

    List<Symptom> findAll();

    List<Symptom> findAllByUserId(long userId);

}
