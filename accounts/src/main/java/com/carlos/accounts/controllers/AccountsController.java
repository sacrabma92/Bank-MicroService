package com.carlos.accounts.controllers;

import com.carlos.accounts.constants.StaticResponseHttp;
import com.carlos.accounts.dto.CustomerDto;
import com.carlos.accounts.dto.ErrorReponseDto;
import com.carlos.accounts.dto.ReponseDto;
import com.carlos.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST API for accounts in Banck",
        description = "CRUD REST API- CREATE, UPDATE, FETCH and DELETE account details"
)
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    @Operation(
            summary = "Create Account REST API",
            description = "API REST to create new Customer & Account inside Bank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ReponseDto> createAccunt(@Valid @RequestBody CustomerDto customerDto){
        // Ejecutamos el servicio para crear cuenta y le pasamos el dto
        iAccountsService.createAccount(customerDto);
        // Retornamos la respuesta si todo salio bien
        return ResponseEntity
                // Mensaje en la cabecera
                .status(HttpStatus.CREATED)
                // Mensaje de retorno
                .body(new ReponseDto(StaticResponseHttp.STATUS_201, StaticResponseHttp.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "API REST to fetch Customer & Account details based on a mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
               @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer & Account details based on account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorReponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/updated")
    public ResponseEntity<ReponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ReponseDto(StaticResponseHttp.STATUS_200, StaticResponseHttp.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ReponseDto(StaticResponseHttp.STATUS_500, StaticResponseHttp.MESSAGE_500));
        }
    }

    @Operation(
            summary = "Delete Account &  Customer Details REST API",
            description = "REST API to delete Customer & Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorReponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ReponseDto> deleteAccountDetails(@RequestParam
               @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber){
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ReponseDto(StaticResponseHttp.STATUS_200, StaticResponseHttp.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ReponseDto(StaticResponseHttp.STATUS_500, StaticResponseHttp.MESSAGE_500));
        }
    }
}
