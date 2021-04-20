package enactApp.enactAPI.data.service;

import enactApp.enactAPI.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendResetPasswordEmail(String email, String token){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Cancer DietAssist Password Reset");
        mail.setText("Looks like you requested to reset your password. Click the link below to change it \n" +
                "https://localhost:7777/api/users/updatePassword/" + token);
        javaMailSender.send(mail);

    }
}
