package com.example.fashionblog.Service;

public interface MailSenderService{
    void sendMail(String recipient, String subject, String body);
}
