package com.example.fashionblog.CustomException;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class ErrorFormat {
    private String message;
    private HttpStatus status;
    private LocalDate timestamp;
}
