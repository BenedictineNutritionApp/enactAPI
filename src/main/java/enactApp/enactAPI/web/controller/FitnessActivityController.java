package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.FitnessActivity;
import enactApp.enactAPI.data.repository.FitnessActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
//        return fitnessActivityRepository.findAll();
    }

//    @PostMapping(value = "api/fitnessActivity/add/{type}/{intensity}/{minutes}")
//    public String addActivity(@PathVariable String type, @PathVariable String intensity, @PathVariable String minutes){
//        FitnessActivity fitnessActivity = new FitnessActivity(type, intensity, minutes);
//        fitnessActivityRepository.save(fitnessActivity);
//        return "added";
//    }

    @PostMapping(path = "/api/fitnessActivity/add/")
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
    @PutMapping(path = "/api/fitnessActivity/update")
    public boolean updateFitnessActivity(@RequestBody FitnessActivity fitnessActivity){
        FitnessActivity oldFitnessActivity = fitnessActivityRepository.getOne(fitnessActivity.getId());
        fitnessActivity.setUpdated(new Date());
        fitnessActivity.setCreated(oldFitnessActivity.getCreated());
        fitnessActivityRepository.save(fitnessActivity);
        return true;
    }
}
