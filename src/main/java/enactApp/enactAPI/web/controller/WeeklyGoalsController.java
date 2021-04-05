package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.WeeklyGoals;
import enactApp.enactAPI.data.repository.WeeklyGoalsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class WeeklyGoalsController {

    @Autowired
    private final WeeklyGoalsRepository weeklyGoalsRepository;

    public WeeklyGoalsController(WeeklyGoalsRepository weeklyGoalsRepository) {
        this.weeklyGoalsRepository = weeklyGoalsRepository;
    }


    @GetMapping(value = "/api/weekly_goals/all")
    public List<WeeklyGoals> getWeeklyGoals() {
        List<WeeklyGoals> weeklyGoalsList = weeklyGoalsRepository.findAll();
        return weeklyGoalsList;
    }

    @PostMapping(path = "/api/weekly_goals/add/")
    public boolean saveWeeklyGoals(@RequestBody WeeklyGoals weeklyGoals) {
        WeeklyGoals newWeeklyGoals = new WeeklyGoals();
        newWeeklyGoals.setType(weeklyGoals.getType());
        newWeeklyGoals.setGoalDescription(weeklyGoals.getGoalDescription());
        newWeeklyGoals.setHelp_info(weeklyGoals.getHelp_info());
        weeklyGoalsRepository.save(newWeeklyGoals);
        System.out.println(newWeeklyGoals.getType());
        return true;

    }
}
