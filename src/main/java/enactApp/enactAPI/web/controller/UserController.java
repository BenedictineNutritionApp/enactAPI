package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.ActivityLevel;
import enactApp.enactAPI.data.model.FrequentGiIssues;
import enactApp.enactAPI.data.model.User;
import enactApp.enactAPI.data.model.UserHasFrequentGiIssues;
import enactApp.enactAPI.data.repository.ActivityLevelRepository;
import enactApp.enactAPI.data.repository.FrequentGiIssueRepository;
import enactApp.enactAPI.data.repository.UserHasFrequentGiIssuesRepository;
import enactApp.enactAPI.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public UserController(UserRepository userRepository, ActivityLevelRepository activityLevelRepository, FrequentGiIssueRepository frequentGiIssueRepository, UserHasFrequentGiIssuesRepository userHasFrequentGiIssueRepository) {
        this.userRepository = userRepository;
        this.activityLevelRepository = activityLevelRepository;
        this.frequentGiIssueRepository = frequentGiIssueRepository;
        this.userHasFrequentGiIssueRepository = userHasFrequentGiIssueRepository;
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
    @PostMapping(value = "api/users/save/{userId}/{birthDate}/{race}/{ethnicity}/{height}/{weight}/{activityLevel}/{gIIssues}/{colonCancer}/{colonStage}/{rectumCancer}/{rectumStage}/{lastDiagDate}/{surgery}/{radiation}/{chemotherapy}/{surgeryType}")
    public String createUser(@PathVariable Long userId, @PathVariable String birthDate,
                             @PathVariable String race,
                             @PathVariable String ethnicity,
                             @PathVariable String height,
                             @PathVariable String weight,
                             @PathVariable String activityLevel,
                             @PathVariable String gIIssues,
                             @PathVariable String colonCancer,
                             @PathVariable String colonStage,
                             @PathVariable String rectumCancer,
                             @PathVariable String rectumStage,
                             @PathVariable String lastDiagDate,
                             @PathVariable String surgery,
                             @PathVariable String radiation,
                             @PathVariable String chemotherapy,
                             @PathVariable String surgeryType) throws ParseException {
        //Checks if the entered email is already in use
        Optional<User> optionalUser = userRepository.findUserById(userId);
        if (optionalUser.isEmpty()) {
            return "error";
        }
        User user = optionalUser.get();
        Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate.split(" ")[0]);
        user.setDateOfBirth(newDate);
        user.setRace(race);
        user.setEthnicity(ethnicity);
        user.setHeight(Integer.parseInt(height));
        user.setWeight(Integer.parseInt(weight));

        Optional<ActivityLevel> activityLevelFromDB = activityLevelRepository.findActivityLevelByLevel(activityLevel);
        if (activityLevelFromDB.isEmpty()) {
            ActivityLevel newActivityLevel = new ActivityLevel();
            newActivityLevel.setLevel(activityLevel);
            newActivityLevel.setCreated(new Date());
            newActivityLevel.setUpdated(new Date());
            activityLevelRepository.save(newActivityLevel);
        }
        activityLevelFromDB = activityLevelRepository.findActivityLevelByLevel(activityLevel);
        Long activityLevelId = activityLevelFromDB.get().getId();
        user.setActivityLevelId(activityLevelId);


        String[] issues = gIIssues.split(",");
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
            userHasFrequentGiIssues.setUserId(userId);
            userHasFrequentGiIssues.setFrequentGiIssuesId(frequentGiIssuesId);
            userHasFrequentGiIssueRepository.save(userHasFrequentGiIssues);
            user.setActivityLevelId(activityLevelId);
        }


        return "";
    }


}
