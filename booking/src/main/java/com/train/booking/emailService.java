package com.train.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class emailService {
    @Autowired
    userRepo userrepo;

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void saveUser (USER user){
        userrepo.save(user);
    }

     @Autowired
    AuthenticationManager authenticationManager;
    public Boolean login(USER user1) {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user1.getEmail(), user1.getPassword()));
            authentication.isAuthenticated();
            return true;

    }

    public  USER getUserByUsername(String username) {
        return userrepo.findByUsername(username);
    } 

}
