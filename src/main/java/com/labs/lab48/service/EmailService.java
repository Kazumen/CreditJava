package com.labs.lab48.service;

import com.labs.lab48.entity.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendEmail(String email, String message)throws MessagingException, UnsupportedEncodingException;
}
