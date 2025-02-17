package com.rescureSync.ResucueSync.service;

import com.rescureSync.ResucueSync.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> saveUser(User user);

    ResponseEntity<?> verifyEmail(String verificationToken);
}
