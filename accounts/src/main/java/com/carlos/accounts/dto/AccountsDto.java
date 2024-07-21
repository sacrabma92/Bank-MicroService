package com.carlos.accounts.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    // Expresion regular para agregar numero de 10 digitos.
    @NotEmpty(message = "AccountNumber can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "AccounType can not be a null or empty")
    private String accountType;

    @NotEmpty(message = "BranchAddress can not be a null or empty")
    private String branchAddress;
}
