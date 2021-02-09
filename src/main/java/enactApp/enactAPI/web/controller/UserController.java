package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.User;
import enactApp.enactAPI.data.model.UserLogin;
import enactApp.enactAPI.data.repository.UserRepository;
import enactApp.enactAPI.data.translator.UserTranslator;
import enactApp.enactAPI.web.models.UserView;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
     * @param password The users password
     * @return The user view
     * @throws Exception
     */
    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @GetMapping(value = "/api/users/{email}/{password}")
    public UserView viewUser(@PathVariable String email, @PathVariable String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByUserEmail(email);
        if (optionalUser.isEmpty()) {
            log.error("User Email: [{}] does not exist.", email);
            throw new Exception("User Email: " + email + " does not exist.");
        }
        return UserTranslator.entityToView(optionalUser.get());
    }

    /**
     * This method is responsible for logging the user into their account
     *
     * @param userLogin The userLogin
     * @return Dsiplay message
     */
    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @PostMapping(value = "api/users/login")
    public String login(@Valid @RequestBody UserLogin userLogin) {
        //Checks that the entered in information is not empty
        String email = userLogin.getEmail();
        String password = userLogin.getPassword();
        if (email.equalsIgnoreCase("") || email.equalsIgnoreCase(" ") || password.equalsIgnoreCase("") || password.equalsIgnoreCase(" ")) {
            return "Invalid";
        }
        // If the user does not exist currently
        Optional<User> optionalUser = userRepository.findByUserEmail(email);
        if (optionalUser.isEmpty()) {
            return "Invalid";
        }

        User user = optionalUser.get();
        //Checks that the entered information is correct against that user's stored information
        String hashedStoredPass = user.getUserPassword();
        String hashedEnteredPass = encode(password);
        System.out.println("stored pass " + hashedStoredPass);
        System.out.println("entered pass " + hashedEnteredPass);
        //If it is incorrect
        if (!hashedEnteredPass.equals(hashedStoredPass)) {
            return "Invalid";
        }
        return "Welcome " + user.getUserEmail() + "!";
    }

    /**
     * Encodes the user's password
     *
     * @param password The password being encoded
     * @return The encoded password
     */
    @SneakyThrows
    public String encode(String password) {
        String encodedPass = "";
        for (int i = 0; i < password.length(); i++) {
            char x = password.charAt(i);
            x += 5;
            encodedPass += x;
        }

//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
//        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//        byte[] hash = factory.generateSecret(spec).getEncoded();
//        String encodedPass = "";
//        for (int i = 0; i < hash.length; i++) {
//            encodedPass+=hash[i];
//        }
        return encodedPass;
    }

    @CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
    @GetMapping(value = "api/users/checkIfEmailExists/{email}")
    public boolean checkIfEmailExists(@PathVariable String email) {
        // If the user does not exist currently
        Optional<User> optionalUser = userRepository.findByUserEmail(email);
        if (optionalUser.isPresent()) {
            return true;
        }else{
            return false;
        }

    }


}
