package com.homebank.account.service;

import com.homebank.account.dto.CustomerDto;
import com.homebank.account.model.Accounts;
import com.homebank.account.model.Customer;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);
    CustomerDto getAccount(String mobileNumber);
}
