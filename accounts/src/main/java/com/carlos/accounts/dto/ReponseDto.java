package com.carlos.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@Schema(
        name = "Reponse",
        description = "Schema to hold successful reponse information"
)
public class ReponseDto {

    @Schema( description = "Status code in the response", example = "200")
    // Error de Respuesta 200, 204 etc..
    private String statusCode;

    @Schema( description = "Status message in the reponse", example = "Request processed successfully")
    // Mensaje de Repuesta
    private String statusMsg;
}
