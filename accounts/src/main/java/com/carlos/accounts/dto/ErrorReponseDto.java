package com.carlos.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorReponseDto {

    // Ruta donde sucedio el error
    private String apiPath;

    // Codigo Http de Error
    private HttpStatus errorCode;

    // Mensaje del error
    private String errorMessage;

    // Hora que sucedio el error.
    private LocalDateTime errorTime;
}
