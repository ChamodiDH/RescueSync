package com.rescureSync.ResucueSync.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {

    public void sendEmail(SimpleMailMessage email);
}
