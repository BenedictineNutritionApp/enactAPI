package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.*;
import enactApp.enactAPI.data.repository.*;
import enactApp.enactAPI.data.translator.FoodLogEntryTranslator;
import enactApp.enactAPI.web.models.FoodLogEntryView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * This is the userController class
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private final UserRepository userRepository;
    private final ActivityLevelRepository activityLevelRepository;
    private final FrequentGiIssueRepository frequentGiIssueRepository;
    private final UserHasFrequentGiIssuesRepository userHasFrequentGiIssueRepository;
    private final FoodLogEntryRepository foodLogEntryRepository;
    private final CancerTreatmentRepository cancerTreatmentRepository;


    public UserController(UserRepository userRepository, ActivityLevelRepository activityLevelRepository, FrequentGiIssueRepository frequentGiIssueRepository, UserHasFrequentGiIssuesRepository userHasFrequentGiIssueRepository, FoodLogEntryRepository foodLogEntryRepository, CancerTreatmentRepository cancerTreatmentRepository) {
        this.userRepository = userRepository;
        this.activityLevelRepository = activityLevelRepository;
        this.frequentGiIssueRepository = frequentGiIssueRepository;
        this.userHasFrequentGiIssueRepository = userHasFrequentGiIssueRepository;
        this.foodLogEntryRepository = foodLogEntryRepository;
        this.cancerTreatmentRepository = cancerTreatmentRepository;
    }


    @CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
    @GetMapping(value = "api/users/checkIfEmailExists/{email}")
    public boolean checkIfEmailExists(@PathVariable String email) {
        // If the user does not exist currently
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        return optionalUser.isPresent();
    }

    /**
     * This method creates the user by setting the user details and saving them to the database, returning error messages or success message when applicable.
     *
     * @param user The user
     * @return The string containing the error type or successful registration pop up
     */
    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @PostMapping(value = "api/users/register")
    public String createUser(@Valid @RequestBody User user) {
        //Checks if the entered email is already in use
        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            return "Username already taken";
        }
        //Sets the user's details and saves them to the database
//        user.setPassword(encode(user.getPassword()));
        user.setPassword(user.getPassword());
        user.setCreated(new Date());
        user.setUpdated(new Date());
        userRepository.save(user);
        System.out.println(user.getEmail());
        return "Registration Successful. You have been logged in.";
    }


    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @GetMapping(value = "api/users/login/{email}/{password}")
    public String login(@PathVariable String email, @PathVariable String password) {
        // If the user does not exist currently
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isEmpty()) {
            return "invalid";
        }
        User user = optionalUser.get();
        //Checks that the entered information is correct against that user's stored information
        String storedPass = user.getPassword();
        //If it is incorrect
        if (!password.equals(storedPass)) {
            return "invalid";
        }
        return "valid";
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @GetMapping(value = "api/users/formstatus/{userId}/")
    public Boolean getFormCompletionStatus(@PathVariable String userId) throws ParseException {
        Optional<User> optionalUser = userRepository.findUserById(Long.parseLong(userId));
        if (optionalUser.isEmpty()) {
            return false;
        }
        User user = optionalUser.get();
        return user.getColorectal() != null;
    }


    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @PostMapping(value = "api/users/form/save/")
    public String saveFormInfo(@Valid @RequestBody FormModel formModel) throws ParseException {
        Optional<User> optionalUser = userRepository.findUserById(formModel.getUserId());
        if (optionalUser.isEmpty()) {
            return "user not found";
        }
        System.out.println(formModel.toString());
        User userFromDB = optionalUser.get();
        Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(formModel.getBirthDate());
        userFromDB.setDateOfBirth(dateOfBirth);
        userFromDB.setRace(formModel.getRace());
        userFromDB.setEthnicity(formModel.getEthnicity());
        userFromDB.setGender(formModel.getGender());
        userFromDB.setHeight(formModel.getHeight());
        userFromDB.setWeight(formModel.getWeight());
        Optional<ActivityLevel> activityLevelFromDB = activityLevelRepository.findActivityLevelByLevel(formModel.getActivityLevel());
        if (activityLevelFromDB.isEmpty()) {
            ActivityLevel newActivityLevel = new ActivityLevel();
            newActivityLevel.setLevel(formModel.getActivityLevel());
            newActivityLevel.setCreated(new Date());
            newActivityLevel.setUpdated(new Date());
            activityLevelRepository.save(newActivityLevel);
        }
        activityLevelFromDB = activityLevelRepository.findActivityLevelByLevel(formModel.getActivityLevel());
        Long activityLevelId = activityLevelFromDB.get().getId();
        userFromDB.setActivityLevelId(activityLevelId);

        String[] issues = formModel.getGastroIntestinalIssues().split(",");
        for (String issue : issues) {
            Optional<FrequentGiIssues> frequentGiIssuesFromDB = frequentGiIssueRepository.findFrequentGiIssuesByIssue(issue);
            if (frequentGiIssuesFromDB.isEmpty()) {
                FrequentGiIssues newFrequentGiIssues = new FrequentGiIssues();
                newFrequentGiIssues.setIssue(issue);
                newFrequentGiIssues.setCreated(new Date());
                newFrequentGiIssues.setUpdated(new Date());
                frequentGiIssueRepository.save(newFrequentGiIssues);
            }
            frequentGiIssuesFromDB = frequentGiIssueRepository.findFrequentGiIssuesByIssue(issue);
            Long frequentGiIssuesId = frequentGiIssuesFromDB.get().getId();
            UserHasFrequentGiIssues userHasFrequentGiIssues = new UserHasFrequentGiIssues();
            userHasFrequentGiIssues.setUserId(formModel.getUserId());
            userHasFrequentGiIssues.setFrequentGiIssuesId(frequentGiIssuesId);
            userHasFrequentGiIssueRepository.save(userHasFrequentGiIssues);
        }
        userFromDB.setColorectal(formModel.getColorectalCancer());
        userFromDB.setStage(Long.parseLong(formModel.getColorectalStage().split(" ")[1]));
        Date lastDiagDate = new SimpleDateFormat("yyyy-MM-dd").parse(formModel.getLastDiagDate());
        userFromDB.setDiagnosisDate(lastDiagDate);

        CancerTreatment cancerTreatment = CancerTreatment.builder()
                .surgery(Boolean.parseBoolean(formModel.getCancerTreatment().split(",")[0]))
                .chemoTherapy(Boolean.parseBoolean(formModel.getCancerTreatment().split(",")[1]))
                .radiationTherapy(Boolean.parseBoolean(formModel.getCancerTreatment().split(",")[2]))
                .other(Boolean.parseBoolean(formModel.getCancerTreatment().split(",")[3]))
                .uncertain(Boolean.parseBoolean(formModel.getCancerTreatment().split(",")[4]))
                .ostomy(Boolean.parseBoolean(formModel.getCancerTreatment().split(",")[5]))
                .userId(formModel.getUserId())
                .created(new Date())
                .updated(new Date())
                .build();
        cancerTreatmentRepository.save(cancerTreatment);

        userRepository.save(userFromDB);
        System.out.println("DONE");


        return "form saved";
    }


    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @GetMapping(value = "api/users/{userId}/foodlog/{date}")
    public List<FoodLogEntryView> getDailyFoodLog(@PathVariable String userId, @PathVariable String date) throws ParseException {
        Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        List<FoodLogEntry> foodLogEntryList = foodLogEntryRepository.findFoodLogEntryByUserIdAndDateOrderByEntryTime(Long.parseLong(userId), parsedDate);
        return FoodLogEntryTranslator.entitiesToViews(foodLogEntryList);

    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @PostMapping(value = "api/users/foodlog/save/{entryTime}")
    public String saveNewFoodLog(@Valid @RequestBody FoodLogEntry foodLogEntry, @PathVariable String entryTime) throws ParseException {
        FoodLogEntry newFoodLogEntry = new FoodLogEntry();
        Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(entryTime.split(" ")[0]);
        Date newEntryTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(entryTime);
        newFoodLogEntry.setEntryTime(newEntryTime);
        newFoodLogEntry.setDate(newDate);
        newFoodLogEntry.setPortion(foodLogEntry.getPortion());
        newFoodLogEntry.setUserId(foodLogEntry.getUserId());
        newFoodLogEntry.setFoodId(foodLogEntry.getFoodId());
        newFoodLogEntry.setCreated(new Date());
        newFoodLogEntry.setUpdated(new Date());
        foodLogEntryRepository.save(newFoodLogEntry);
        return "new food log entry saved";
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @PutMapping(value = "api/users/foodlog/update")
    public String updateFoodLog(@Valid @RequestBody FoodLogEntry foodLogEntry) {
        Optional<FoodLogEntry> optionalFoodLogEntry = foodLogEntryRepository.findFoodLogEntryById(foodLogEntry.getId());
        if (optionalFoodLogEntry.isEmpty()) {
            return "error updating";
        }
        FoodLogEntry foodLogEntryFromDB = optionalFoodLogEntry.get();
        foodLogEntryFromDB.setEntryTime(foodLogEntry.getDate());
        foodLogEntryFromDB.setPortion(foodLogEntry.getPortion());
        foodLogEntryFromDB.setUpdated(new Date());
        foodLogEntryRepository.save(foodLogEntryFromDB);
        return "food log entry updated";
    }

    @Transactional
    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @DeleteMapping(value = "api/users/foodlog/delete/{id}")
    public void deleteFoodLogEntry(@PathVariable Long id) {
        foodLogEntryRepository.deleteById(id);
    }


}
