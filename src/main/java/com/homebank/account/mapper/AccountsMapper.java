package com.homebank.account.mapper;

import com.homebank.account.dto.AccountDto;
import com.homebank.account.model.Accounts;

public class AccountsMapper {

    public static AccountDto mapToAccountsDto(Accounts accounts, AccountDto accountDto){
        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        accountDto.setAccountType(accounts.getAccountType());
        return accountDto;
    }

    public static Accounts mapToAccount(AccountDto accountDto, Accounts accounts){
        accounts.setAccountNumber(accountDto.getAccountNumber());
        accounts.setBranchAddress(accountDto.getBranchAddress());
        accounts.setAccountType(accountDto.getAccountType());
        return accounts;
    }
}
