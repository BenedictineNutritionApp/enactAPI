package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.*;
import enactApp.enactAPI.data.repository.*;
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
@RequestMapping("/api/fitnessActivity")
public class FitnessActivityController {

    @Autowired
    private FitnessActivityRepository fitnessActivityRepository;



    @GetMapping(value = "/all")
    public List<FitnessActivity> getAllFitnessActivity() {
        List<FitnessActivity> fitnessActivityList = fitnessActivityRepository.findAll();
        Collections.sort(fitnessActivityList);
        return fitnessActivityList;
//        return fitnessActivityRepository.findAll();
    }

//    @PostMapping(value = "api/fitnessActivity/add/{type}/{intensity}/{minutes}")
//    public String addActivity(@PathVariable String type, @PathVariable String intensity, @PathVariable String minutes){
//        FitnessActivity fitnessActivity = new FitnessActivity(type, intensity, minutes);
//        fitnessActivityRepository.save(fitnessActivity);
//        return "added";
//    }

    @PostMapping(path = "/add/")
    public boolean saveFitnessActivity(@RequestBody FitnessActivity fitnessActivity) {
        FitnessActivity newFitnessActivity = new FitnessActivity();
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
    public boolean updateFitnessActivity(@RequestBody FitnessActivity fitnessActivity){
        FitnessActivity oldFitnessActivity = fitnessActivityRepository.getOne(fitnessActivity.getId());
        fitnessActivity.setUpdated(new Date());
        fitnessActivity.setCreated(oldFitnessActivity.getCreated());
        fitnessActivityRepository.save(fitnessActivity);
        return true;
    }

}
