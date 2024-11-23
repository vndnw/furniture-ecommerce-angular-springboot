package com.store.furniture.service;

import com.store.furniture.dto.request.EmailDetailsRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @NonFinal
    @Value("${spring.mail.username}")
    String sender;

    public String sendSimpleMail(EmailDetailsRequest emailDetailsRequest) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(sender);
        mailMessage.setTo(emailDetailsRequest.getRecipient());
        mailMessage.setSubject(emailDetailsRequest.getSubject());
        mailMessage.setText(emailDetailsRequest.getMessage());

        javaMailSender.send(mailMessage);

        return "Email sent successfully!";
    }
}
