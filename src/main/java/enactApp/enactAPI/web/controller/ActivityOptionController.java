package enactApp.enactAPI.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import enactApp.enactAPI.data.model.ActivityOption;
import enactApp.enactAPI.data.repository.ActivityOptionRepository;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/activityOptions")
public class ActivityOptionController {

    @Autowired
    private ActivityOptionRepository activityOptionRepository;


    @GetMapping(value = "/all")
    public List<ActivityOption> getAllFitnessActivity() {
        return activityOptionRepository.findAll();
    }

}
