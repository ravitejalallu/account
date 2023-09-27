package com.homebank.account.exceptions;

import com.homebank.account.dto.ErrorResposnseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResposnseDto>handleCustomerException(CustomerAlreadyExistsException exception, WebRequest request){
        ErrorResposnseDto errorResposnseDto = new ErrorResposnseDto(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResposnseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResposnseDto>handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        ErrorResposnseDto errorResposnseDto = new ErrorResposnseDto(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResposnseDto,HttpStatus.NOT_FOUND);
    }
}
