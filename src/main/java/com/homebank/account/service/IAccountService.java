package com.homebank.account.service;

import com.homebank.account.dto.CustomerDto;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);
    CustomerDto getAccount(String mobileNumber);
    CustomerDto updateAccount(CustomerDto customerDto);
}
