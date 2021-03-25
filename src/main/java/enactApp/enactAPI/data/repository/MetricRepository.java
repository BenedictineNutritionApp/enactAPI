package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.FitnessActivity;
import enactApp.enactAPI.data.model.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetricRepository  extends JpaRepository<Metric, Long> {
    List<Metric> findAll();

    List<Metric> findAllByUserId(long userId);

}
