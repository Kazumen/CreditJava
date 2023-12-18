package com.labs.lab48.serviceImpl;

import com.labs.lab48.entity.User;
import com.labs.lab48.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private JavaMailSender mailSender;
    @Async
    @Override
    public void sendEmail(String email, String exception) throws MessagingException, UnsupportedEncodingException {
        String fromAddress = "gametavernbot@gmail.com";
        String senderName = "BankOrg";
        String subject = "Here is critical error!";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(exception, true);
        mailSender.send(message);
    }
}
