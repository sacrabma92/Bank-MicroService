package com.carlos.accounts.service;

import com.carlos.accounts.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface IAccountsService {

    /**
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based an a given mobileNumber
     */
    CustomerDto fetchAccount(String mobileNumber);
}
