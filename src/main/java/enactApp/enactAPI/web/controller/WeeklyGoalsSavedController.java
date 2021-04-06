package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.WeeklyGoalsSaved;
import enactApp.enactAPI.data.repository.WeeklyGoalsSavedRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@RestController
public class WeeklyGoalsSavedController {

    @Autowired
    private final WeeklyGoalsSavedRepository weeklyGoalsSavedRepository;

    public WeeklyGoalsSavedController(WeeklyGoalsSavedRepository weeklyGoalsSavedRepository) {
        this.weeklyGoalsSavedRepository = weeklyGoalsSavedRepository;
    }


    @GetMapping(value = "/api/weekly_goals_saved/all")
    public List<WeeklyGoalsSaved> getWeeklyGoalsSaved() {
        List<WeeklyGoalsSaved> weeklyGoalsSavedList = weeklyGoalsSavedRepository.findAll();
        return weeklyGoalsSavedList;
    }

    @PostMapping(path = "/api/weekly_goals_saved/add/")
    public boolean saveWeeklyGoalsSaved(@RequestBody WeeklyGoalsSaved weeklyGoalsSaved) {
        long i = 0;
        i = 1+i;
        WeeklyGoalsSaved newWeeklyGoalsSaved = new WeeklyGoalsSaved();
        newWeeklyGoalsSaved.setId(weeklyGoalsSaved.getId());
        newWeeklyGoalsSaved.setType(weeklyGoalsSaved.getType());
        newWeeklyGoalsSaved.setGoalDescription(weeklyGoalsSaved.getGoalDescription());
        newWeeklyGoalsSaved.setHelp_info(weeklyGoalsSaved.getHelp_info());
        newWeeklyGoalsSaved.setUserId(i);
        weeklyGoalsSavedRepository.save(newWeeklyGoalsSaved);
        System.out.println(newWeeklyGoalsSaved.getType());
        return true;

    }

    @Transactional
    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @DeleteMapping(value = "/api/weekly_goals_saved/delete/{id}")
    public void deleteWeeklyGoalsSavedByID(@PathVariable("id") Long id) { weeklyGoalsSavedRepository.deleteById(id); }
}
