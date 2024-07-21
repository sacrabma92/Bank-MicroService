package com.carlos.accounts.exception;

import com.carlos.accounts.dto.ErrorReponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejar exceptiones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorReponseDto> handleGlobalException(Exception exception,
                                                                               WebRequest webRequest){
        ErrorReponseDto errorReponseDto = new ErrorReponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorReponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Manejar el error de Cliente ya existe
    @ExceptionHandler(CustomerAlreadyException.class)
    public ResponseEntity<ErrorReponseDto> handleCustomerAlreadyExistException(CustomerAlreadyException exception,
                                                                          WebRequest webRequest){
        ErrorReponseDto errorReponseDto = new ErrorReponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorReponseDto, HttpStatus.BAD_REQUEST);
    }

    // Manejar el error cuando no encuentra un number de telefono
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorReponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                               WebRequest webRequest){
        ErrorReponseDto errorReponseDto = new ErrorReponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorReponseDto, HttpStatus.NOT_FOUND);
    }
}
