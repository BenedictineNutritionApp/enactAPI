package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.FitnessActivity;
import enactApp.enactAPI.data.model.Food;
import enactApp.enactAPI.data.model.User;
import enactApp.enactAPI.data.repository.FitnessActivityRepository;
import enactApp.enactAPI.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        return fitnessActivityRepository.findAll();
    }

//    @PostMapping(value = "api/fitnessActivity/add/{type}/{intensity}/{minutes}")
//    public String addActivity(@PathVariable String type, @PathVariable String intensity, @PathVariable String minutes){
//        FitnessActivity fitnessActivity = new FitnessActivity(type, intensity, minutes);
//        fitnessActivityRepository.save(fitnessActivity);
//        return "added";
//    }

    @PostMapping(path = "/api/fitnessActivity/add/")
    public boolean saveFitnessActivity(@RequestBody FitnessActivity fitnessActivity){
        FitnessActivity newFitnessActivity = new FitnessActivity();
        newFitnessActivity.setIntensity(fitnessActivity.getIntensity());
        newFitnessActivity.setMinutes(fitnessActivity.getMinutes());
        newFitnessActivity.setType(fitnessActivity.getType());
        newFitnessActivity.setUpdated(new Date());
        newFitnessActivity.setCreated(new Date());
        fitnessActivityRepository.save(newFitnessActivity);
        System.out.println(newFitnessActivity.getType());
        return true;

    }
}
