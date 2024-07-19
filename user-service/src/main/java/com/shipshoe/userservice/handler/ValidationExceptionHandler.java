package com.shipshoe.userservice.handler;

import com.shipshoe.userservice.dto.UserResponseValidationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<UserResponseValidationDto> validationExceptionHandler(Exception exception) {
        return UserResponseValidationDto.validationFail();
    }
}
