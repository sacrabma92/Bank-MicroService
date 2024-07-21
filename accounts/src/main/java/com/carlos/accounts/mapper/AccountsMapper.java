package com.carlos.accounts.mapper;

import com.carlos.accounts.dto.AccountsDto;
import com.carlos.accounts.entity.Accounts;

public class AccountsMapper {

    // Map Entity to Dto
    public static AccountsDto mapToAccountsDto(Accounts accounts,
                                               AccountsDto accountsDto){
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    // Map Dto to Entity
    public static Accounts mapToAccounts(AccountsDto accountsDto,
                                         Accounts accounts){
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
