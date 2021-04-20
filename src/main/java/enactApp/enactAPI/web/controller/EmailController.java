package enactApp.enactAPI.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @RequestMapping(value = "sendemail")
    public String sendEmail(){
        return "Email sent successfully";
    }
}
