package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.User;
import enactApp.enactAPI.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
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


}
