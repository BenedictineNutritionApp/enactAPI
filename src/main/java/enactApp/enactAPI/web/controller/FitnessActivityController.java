package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.FitnessActivity;
import enactApp.enactAPI.data.model.Metric;
import enactApp.enactAPI.data.repository.FitnessActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import enactApp.enactAPI.data.model.*;
import enactApp.enactAPI.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import enactApp.enactAPI.data.model.ActivityOption;
import enactApp.enactAPI.data.repository.ActivityOptionRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/fitnessActivity")
public class FitnessActivityController {

    @Autowired
    private FitnessActivityRepository fitnessActivityRepository;


    @GetMapping(value = "/all")
    public List<FitnessActivity> getAllFitnessActivity() {
        List<FitnessActivity> fitnessActivityList = fitnessActivityRepository.findAll();
        Collections.sort(fitnessActivityList);
        return fitnessActivityList;
    }

    @GetMapping(value = "/all/user")
    public List<FitnessActivity> getAllFitnessActivityByUserId(@RequestParam String userId) {
        List<FitnessActivity> fitnessActivityList = fitnessActivityRepository.findAllByUserId(Integer.parseInt(userId));
        Collections.sort(fitnessActivityList);
        return fitnessActivityList;
    }

    @PostMapping(path = "/add/")
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

    @PutMapping(path = "/update")
    public boolean updateFitnessActivity(@RequestBody FitnessActivity fitnessActivity) {
        FitnessActivity oldFitnessActivity = fitnessActivityRepository.getOne(fitnessActivity.getId());
        fitnessActivity.setUpdated(new Date());
        fitnessActivity.setCreated(oldFitnessActivity.getCreated());
        fitnessActivityRepository.save(fitnessActivity);
        return true;
    }


    @GetMapping(value = "/week/user/")
    public int getWeekFitnessActivity(@RequestParam String numberOfDays, @RequestParam String intensity, @RequestParam String userId) {
        LocalDateTime startDate = LocalDateTime.now().minus(Duration.ofDays(Long.parseLong(numberOfDays)));
        List<FitnessActivity> fitnessActivityList = fitnessActivityRepository.findFitnessActivitiesByDateTimeAfterAndAndIntensityIsGreaterThanAndUserId(startDate, intensity, Integer.parseInt(userId));
        int totalWeeklyMinutes = 0;
        for (FitnessActivity fa : fitnessActivityList) {
            totalWeeklyMinutes = totalWeeklyMinutes + Integer.parseInt(fa.getMinutes());
        }
        return totalWeeklyMinutes;
    }

    @GetMapping(value = "/day/user")
    public List<FitnessActivity> getDayActivities(@RequestParam String userId) {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("America/Montreal"));
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        LocalDateTime yesterdayMidnight = todayMidnight.minusDays(1);
        List<FitnessActivity> fitnessActivityList = fitnessActivityRepository.findFitnessActivitiesByDateTimeAfterAndUserId(yesterdayMidnight, Integer.parseInt(userId));
        return fitnessActivityList;
    }
}
