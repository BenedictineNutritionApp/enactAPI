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
@RequestMapping("/api/weekly_goals")
public class WeeklyGoalsController {


    @Autowired
    private WeeklyGoalsRepository weeklyGoalsRepository;


    @GetMapping(value = "/all")
    public List<WeeklyGoals> getWeeklyGoals() {
        List<WeeklyGoals> weeklyGoalsList = weeklyGoalsRepository.findAll();
        return weeklyGoalsList;
    }

    @PostMapping(path = "/add/")
    public boolean saveFitnessActivity(@RequestBody WeeklyGoals weeklyGoals) {
        WeeklyGoals newWeeklyGoals = new WeeklyGoals();
        newWeeklyGoals.setType(weeklyGoals.getType());
        newWeeklyGoals.setGoalDescription(weeklyGoals.getGoalDescription());
        newWeeklyGoals.setHelp_info(weeklyGoals.getHelp_info());
        weeklyGoalsRepository.save(newWeeklyGoals);
        System.out.println(newWeeklyGoals.getType());
        return true;

    }


}
