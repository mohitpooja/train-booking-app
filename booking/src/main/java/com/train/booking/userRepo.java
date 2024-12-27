package com.train.booking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<USER, Long> {
       USER findByUsername(String username);
}
