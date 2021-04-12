package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.*;
import enactApp.enactAPI.data.repository.*;
import enactApp.enactAPI.data.translator.FoodTranslator;
import enactApp.enactAPI.web.models.FoodView;
import enactApp.enactAPI.web.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import enactApp.enactAPI.data.model.ActivityOption;
import enactApp.enactAPI.data.repository.ActivityOptionRepository;
import org.springframework.http.ResponseEntity;
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

    @PreAuthorize("hasRole('BASE') or hasRole('SUPER') or hasRole('MASTER')")
    @PostMapping(value = "/create")
    public ResponseEntity<?> createGoal(@Valid @RequestBody WeeklyGoals weeklyGoal) {
        Optional<WeeklyGoals> optionalWeeklyGoal = weeklyGoalsRepository.findWeeklyGoalsByGoalDescription(weeklyGoal.getGoalDescription());
        if (optionalWeeklyGoal.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Goal already exists!"));
        } else {
            List<WeeklyGoals> weeklyGoals = weeklyGoalsRepository.findAll();
            WeeklyGoals newWeeklyGoal = WeeklyGoals.builder()
                    .id((long) (weeklyGoals.size() + 1))
                    .goalDescription(weeklyGoal.getGoalDescription())
                    .type(weeklyGoal.getType())
                    .help_info(weeklyGoal.getHelp_info())
                    .build();
            weeklyGoalsRepository.save(newWeeklyGoal);
            return ResponseEntity.ok(new MessageResponse("Goal saved successfully!"));
        }
    }

    @PreAuthorize("hasRole('BASE') or hasRole('SUPER') or hasRole('MASTER')")
    @PostMapping(value = "/edit")
    public ResponseEntity<?> editGoal(@Valid @RequestBody WeeklyGoals weeklyGoal) {
        Optional<WeeklyGoals> optionalWeeklyGoal = weeklyGoalsRepository.findWeeklyGoalsById(weeklyGoal.getId());
        if (optionalWeeklyGoal.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Goal does not exist!"));
        } else {
            WeeklyGoals updatedWeeklyGoal = optionalWeeklyGoal.get();
            updatedWeeklyGoal.setGoalDescription(weeklyGoal.getGoalDescription());
            updatedWeeklyGoal.setHelp_info(weeklyGoal.getHelp_info());
            updatedWeeklyGoal.setType(weeklyGoal.getType());
            weeklyGoalsRepository.save(updatedWeeklyGoal);
            return ResponseEntity.ok(new MessageResponse("Goal updated successfully!"));
        }
    }


}
