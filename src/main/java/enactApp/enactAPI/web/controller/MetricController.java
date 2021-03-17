package enactApp.enactAPI.web.controller;
import enactApp.enactAPI.data.model.*;
import enactApp.enactAPI.data.repository.FitnessActivityRepository;
import enactApp.enactAPI.data.repository.MetricRepository;
import enactApp.enactAPI.data.repository.SymptomRepository;
import enactApp.enactAPI.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class MetricController {
    @Autowired
    private final MetricRepository metricRepository;

    public MetricController(MetricRepository metricRepository){
        this.metricRepository = metricRepository;
    }
    @GetMapping(value = "/api/metric/all")
    public List<Metric> getAllMetric() {
        List<Metric> metricList = metricRepository.findAll();
        Collections.sort(metricList);
        return metricList;
    }

    @PostMapping(path = "/api/metric/add/")
    public boolean saveMetric(@RequestBody Metric metric){
        Metric newMetric = new Metric();
        newMetric.setWeight(metric.getWeight());
        newMetric.setDateTime(metric.getDateTime());
        newMetric.setUpdated(new Date());
        newMetric.setCreated(new Date());
        metricRepository.save(newMetric);
        return true;
    }
}
