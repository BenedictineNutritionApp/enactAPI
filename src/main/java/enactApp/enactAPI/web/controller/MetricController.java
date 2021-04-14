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
import java.time.*;
import java.util.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/metric")
public class MetricController {
    @Autowired
    private MetricRepository metricRepository;

    public MetricController(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;
    }

    @GetMapping(value = "/all")
    public List<Metric> getAllMetric() {
        List<Metric> metricList = metricRepository.findAll();
        Collections.sort(metricList);
        return metricList;
    }

    @GetMapping(value = "/all/user")
    public List<Metric> getAllMetricByUserId(@RequestParam String userId) {
        List<Metric> metricList = metricRepository.findAllByUserId(Integer.parseInt(userId));
        Collections.sort(metricList);
        return metricList;
    }

    @PostMapping(path = "/add/")
    public boolean saveMetric(@RequestBody Metric metric) {
        Metric newMetric = new Metric();
        System.out.println(metric.getUserId());
        newMetric.setUserId(metric.getUserId());
        newMetric.setWeight(metric.getWeight());
        newMetric.setDateTime(metric.getDateTime());
        newMetric.setUpdated(new Date());
        newMetric.setCreated(new Date());
        metricRepository.save(newMetric);
        return true;
    }

    @GetMapping(path = "/user/range")
    public List<Metric> getRangeMetricByUserId(@RequestParam String userId, @RequestParam String numberOfDays) {
        LocalDateTime startDate = LocalDateTime.now().minus(Duration.ofDays(Long.parseLong(numberOfDays)));
        List<Metric> metricList = metricRepository.findAllByUserId(Integer.parseInt(userId));
        Collections.sort(metricList);
        return metricList;
    }

    @GetMapping(value = "/day/user")
    public List<Metric> getDayMetrics(@RequestParam String userId) {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("America/Montreal"));
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        LocalDateTime yesterdayMidnight = todayMidnight.minusDays(1);
        List<Metric> metricList = metricRepository.findMetricByDateTimeAfterAndUserId(yesterdayMidnight, Integer.parseInt(userId));
        return metricList;
    }
}
