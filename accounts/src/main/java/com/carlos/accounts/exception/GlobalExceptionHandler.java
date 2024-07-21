package com.carlos.accounts.exception;

import com.carlos.accounts.dto.ErrorReponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    // Exception cuando surge errores de @Validacion
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders httpHeaders, HttpStatusCode status, WebRequest  request){
        Map<String, String> validationErrors = new HashMap<>();
        // Aqui nos dara TODOS los errores que surgieron.
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        // Itero la Lista de Errores y Muestro el campo donde sucedio y el mensaje de error
        validationErrorList.forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

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
