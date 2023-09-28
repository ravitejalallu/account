package com.homebank.account.mapper;

import com.homebank.account.dto.AccountDto;
import com.homebank.account.model.Account;

public class AccountsMapper {

    public static AccountDto mapToAccountsDto(Account account, AccountDto accountDto){
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setBranchAddress(account.getBranchAddress());
        accountDto.setAccountType(account.getAccountType());
        return accountDto;
    }

    public static Account mapToAccount(AccountDto accountDto, Account account){
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setBranchAddress(accountDto.getBranchAddress());
        account.setAccountType(accountDto.getAccountType());
        return account;
    }
}
