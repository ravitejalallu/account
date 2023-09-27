package com.homebank.account.service.impl;

import com.homebank.account.constants.AccountsConstants;
import com.homebank.account.dto.AccountDto;
import com.homebank.account.dto.CustomerDto;
import com.homebank.account.exceptions.CustomerAlreadyExistsException;
import com.homebank.account.exceptions.ResourceNotFoundException;
import com.homebank.account.mapper.AccountsMapper;
import com.homebank.account.mapper.CustomerMapper;
import com.homebank.account.model.Accounts;
import com.homebank.account.model.Customer;
import com.homebank.account.repository.AccountsRepository;
import com.homebank.account.repository.CustomerRepository;
import com.homebank.account.service.IAccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl  implements IAccountService {

    private final CustomerRepository customerRepository;
    private final AccountsRepository accountsRepository;

    public AccountServiceImpl(CustomerRepository customerRepository, AccountsRepository accountsRepository) {
        this.customerRepository = customerRepository;
        this.accountsRepository = accountsRepository;
    }

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
         customerRepository.findByMobileNumber(customer.getMobileNumber()).ifPresent(customer1 -> {
            throw new CustomerAlreadyExistsException("customer already registered with this mobile number "+customer.getMobileNumber());
        });

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);

        accountsRepository.save(createNewAccount(savedCustomer));

    }

    @Override
    public CustomerDto getAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("account", "customerId", customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        AccountDto accountDto = AccountsMapper.mapToAccountsDto(accounts, new AccountDto());
        customerDto.setAccountDto(accountDto);
        return customerDto;


    }

    private Accounts createNewAccount(Customer customer){
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        accounts.setAccountNumber(UUID.randomUUID().toString());
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setCreatedAt(LocalDateTime.now());
        accounts.setCreatedBy("Anonymous");
        return  accounts;
    }
}
