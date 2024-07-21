package com.carlos.accounts.controllers;

import com.carlos.accounts.constants.StaticResponseHttp;
import com.carlos.accounts.dto.CustomerDto;
import com.carlos.accounts.dto.ReponseDto;
import com.carlos.accounts.entity.Accounts;
import com.carlos.accounts.service.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

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

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
               @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

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
