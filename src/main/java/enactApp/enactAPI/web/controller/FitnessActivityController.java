package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.FitnessActivity;
import enactApp.enactAPI.data.model.Metric;
import enactApp.enactAPI.data.repository.FitnessActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class FitnessActivityController {

    @Autowired
    private final FitnessActivityRepository fitnessActivityRepository;

    public FitnessActivityController(FitnessActivityRepository fitnessActivityRepository) {
        this.fitnessActivityRepository = fitnessActivityRepository;
    }


    @GetMapping(value = "/api/fitnessActivity/all")
    public List<FitnessActivity> getAllFitnessActivity() {
        List<FitnessActivity> fitnessActivityList = fitnessActivityRepository.findAll();
        Collections.sort(fitnessActivityList);
        return fitnessActivityList;
    }

    @GetMapping(value = "/api/fitnessActivity/all/user")
    public List<FitnessActivity> getAllFitnessActivityByUserId(@RequestParam String userId) {
        List<FitnessActivity> fitnessActivityList = fitnessActivityRepository.findAllByUserId(Integer.parseInt(userId));
        Collections.sort(fitnessActivityList);
        return fitnessActivityList;
    }

    @PostMapping(path = "/api/fitnessActivity/add/")
    public boolean saveFitnessActivity(@RequestBody FitnessActivity fitnessActivity) {
        System.out.println(fitnessActivity.getDateTime());
        FitnessActivity newFitnessActivity = new FitnessActivity();
        newFitnessActivity.setUserId(fitnessActivity.getUserId());
        newFitnessActivity.setIntensity(fitnessActivity.getIntensity());
        newFitnessActivity.setMinutes(fitnessActivity.getMinutes());
        newFitnessActivity.setType(fitnessActivity.getType());
        newFitnessActivity.setDateTime(fitnessActivity.getDateTime());
        newFitnessActivity.setUpdated(new Date());
        newFitnessActivity.setCreated(new Date());
        fitnessActivityRepository.save(newFitnessActivity);
        System.out.println(newFitnessActivity.getType());
        return true;

    }

    @PutMapping(path = "/api/fitnessActivity/update")
    public boolean updateFitnessActivity(@RequestBody FitnessActivity fitnessActivity) {
        FitnessActivity oldFitnessActivity = fitnessActivityRepository.getOne(fitnessActivity.getId());
        fitnessActivity.setUpdated(new Date());
        fitnessActivity.setCreated(oldFitnessActivity.getCreated());
        fitnessActivityRepository.save(fitnessActivity);
        return true;
    }

    @GetMapping(value = "/api/fitnessActivity/week/user/")
    public int getWeekFitnessActivity(@RequestParam String numberOfDays, @RequestParam String intensity, @RequestParam String userId) {
        LocalDateTime startDate = LocalDateTime.now().minus(Duration.ofDays(Long.parseLong(numberOfDays)));
        List<FitnessActivity> fitnessActivityList = fitnessActivityRepository.findFitnessActivitiesByDateTimeAfterAndAndIntensityIsGreaterThanAndUserId(startDate, intensity, Integer.parseInt(userId));
        int totalWeeklyMinutes = 0;
        for(FitnessActivity fa : fitnessActivityList){
            totalWeeklyMinutes = totalWeeklyMinutes + Integer.parseInt(fa.getMinutes());
        }
        return totalWeeklyMinutes;
    }
}
