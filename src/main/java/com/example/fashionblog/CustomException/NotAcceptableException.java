package com.example.fashionblog.CustomException;

public class NotAcceptableException extends RuntimeException{
    public NotAcceptableException(String message) {
        super(message);
    }
}
