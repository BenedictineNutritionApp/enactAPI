package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.User;
import enactApp.enactAPI.data.repository.UserRepository;
import enactApp.enactAPI.data.translator.UserTranslator;
import enactApp.enactAPI.web.models.UserView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Finds and returns the user's view
     *
     * @param email The users email
     * @return The user view
     * @throws Exception
     */
    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @GetMapping(value = "/api/users/{email}")
    public UserView viewUser(@PathVariable String email) throws Exception {
        Optional<User> optionalUser = userRepository.findByUserEmail(email);
        if (optionalUser.isEmpty()) {
            log.error("User Email: [{}] does not exist.", email);
            throw new Exception("User Email: " + email + " does not exist.");
        }
        return UserTranslator.entityToView(optionalUser.get());
    }


}
