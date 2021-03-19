package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.ActivityOption;
import enactApp.enactAPI.data.repository.ActivityOptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
public class ActivityOptionController {

    @Autowired
    private final ActivityOptionRepository activityOptionRepository;

    public ActivityOptionController(ActivityOptionRepository activityOptionRepository) {
        this.activityOptionRepository = activityOptionRepository;
    }


    @GetMapping(value = "/api/activityOptions/all")
    public List<ActivityOption> getAllFitnessActivity() {
        return activityOptionRepository.findAll();
    }


}
