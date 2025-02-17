package com.rescureSync.ResucueSync.service;

import com.rescureSync.ResucueSync.entity.User;
import com.rescureSync.ResucueSync.entity.VerificationToken;
import com.rescureSync.ResucueSync.repository.UserRepository;
import com.rescureSync.ResucueSync.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    EmailService emailService;
    @Override
    public ResponseEntity<?> saveUser(User user) {

        if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        userRepository.save(user);

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setVerificationToken(UUID.randomUUID().toString());
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/confirm-account?token="+verificationToken.getVerificationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + verificationToken.getVerificationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    @Override
    public ResponseEntity<?> verifyEmail(String verificationToken) {
        VerificationToken token = verificationTokenRepository.findByVerificationToken(verificationToken);
        if(token != null)
        {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnable(true);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }
    
}
