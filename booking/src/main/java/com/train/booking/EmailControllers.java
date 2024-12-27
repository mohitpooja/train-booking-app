package com.train.booking;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;


@Controller
public class EmailControllers {
    @Autowired
    emailService emailss;



    @GetMapping("/")
    public String returnHome(){
        return "redirect:/index.html";
    }
    @PostMapping ("/login")
    public ResponseEntity<?> returnloign(@RequestBody USER user1){
        boolean isAuthenticated = emailss.login(user1);

        if (isAuthenticated) {
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } else {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "Authentication failed"));
        }

    } 
    @PostMapping("/book-ticket")
    public ResponseEntity<?> bookTicket(@RequestBody Booking request) {

        emailss.sendEmail("sudarshankarn07@gmail.com", "New Booking Request",
                "Booking Details: " + request.toString());
        return ResponseEntity.ok(Collections.singletonMap("success", true));
    }

    @PostMapping("/signupR")
    public ResponseEntity<?> signup(@RequestBody USER user){
       emailss.saveUser(user);
       return ResponseEntity.ok(Collections.singletonMap("success",true));
    }
    

        @GetMapping("/profile")
        public ResponseEntity<USER> getUserProfile() {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            USER user = emailss.getUserByUsername(username);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        


}
