package com.train.booking;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userDetService implements UserDetailsService {
    @Autowired
    userRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        USER user = repo.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("user not found");

        }
        return new userPrincipal(user);
    }
}
