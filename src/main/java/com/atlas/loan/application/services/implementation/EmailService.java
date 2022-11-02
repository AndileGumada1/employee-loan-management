package com.atlas.loan.application.services.implementation;

import com.atlas.loan.application.services.EmailSender;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
//@AllArgsConstructor
@Configuration
public class EmailService implements EmailSender {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private  JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
       try{
           MimeMessage mimeMessage = mailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
           helper.setText(email, true);
           helper.setTo(to);
           helper.setSubject("Confirm your email");
           helper.setFrom("andile@gmail.com");
           mailSender.send(mimeMessage);
       }catch (MessagingException exception){
           LOGGER.error("Failed to send email", exception);
           throw new IllegalStateException("Failed to send email");
       }
    }
    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }
}
