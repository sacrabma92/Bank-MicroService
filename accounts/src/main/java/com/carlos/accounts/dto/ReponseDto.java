package com.carlos.accounts.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class ReponseDto {

    // Error de Respuesta 200, 204 etc..
    private String statusCode;

    // Mensaje de Repuesta
    private String statusMsg;
}
