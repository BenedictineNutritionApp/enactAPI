package enactApp.enactAPI.web.controller;
import enactApp.enactAPI.data.model.*;
import enactApp.enactAPI.data.repository.*;
import enactApp.enactAPI.data.translator.FoodTranslator;
import enactApp.enactAPI.web.models.FoodView;
import org.springframework.beans.factory.annotation.Autowired;
import enactApp.enactAPI.data.model.ActivityOption;
import enactApp.enactAPI.data.repository.ActivityOptionRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/metric")
public class MetricController {
    @Autowired
    private MetricRepository metricRepository;

    @GetMapping(value = "/all")
    public List<Metric> getAllMetric() {
        List<Metric> metricList = metricRepository.findAll();
        Collections.sort(metricList);
        return metricList;
    }

    @PostMapping(path = "/add/")
    @GetMapping(value = "/all/user")
    public List<Metric> getAllMetricByUserId(@RequestParam String userId) {
        List<Metric> metricList = metricRepository.findAllByUserId(Integer.parseInt(userId));
        Collections.sort(metricList);
        return metricList;
    }

    @PostMapping(path = "/api/metric/add/")
    public boolean saveMetric(@RequestBody Metric metric){
        Metric newMetric = new Metric();
        newMetric.setUserId(metric.getUserId());
        newMetric.setWeight(metric.getWeight());
        newMetric.setDateTime(metric.getDateTime());
        newMetric.setUpdated(new Date());
        newMetric.setCreated(new Date());
        metricRepository.save(newMetric);
        return true;
    }
}
