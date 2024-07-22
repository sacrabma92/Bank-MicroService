package com.carlos.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorReponseDto {

    @Schema(description = "API path invoked by client")
    // Ruta donde sucedio el error
    private String apiPath;

    @Schema(description = "Error code representing the error happened")
    // Codigo Http de Error
    private HttpStatus errorCode;

    @Schema( description = "Error message representing the error happened")
    // Mensaje del error
    private String errorMessage;

    @Schema( description = "Time representing the error happened")
    // Hora que sucedio el error.
    private LocalDateTime errorTime;
}
