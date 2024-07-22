package com.carlos.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @Schema(description = "AccountNumber  of the bank account", example = "8123654952")
    // Expresion regular para agregar numero de 10 digitos.
    @NotEmpty(message = "AccountNumber can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private Long accountNumber;

    @Schema(description = "Account type of Bank account", example = "Savings")
    @NotEmpty(message = "AccounType can not be a null or empty")
    private String accountType;

    @Schema(description = "Bank branch address", example = "Calle 12 # 43")
    @NotEmpty(message = "BranchAddress can not be a null or empty")
    private String branchAddress;
}
