package com.example.fashionblog.CustomException;

public class MailNotSentException extends RuntimeException{
    public MailNotSentException(String message) {
        super(message);
    }
}
