package com.homebank.account.service.impl;

import com.homebank.account.constants.AccountsConstants;
import com.homebank.account.dto.AccountDto;
import com.homebank.account.dto.CustomerDto;
import com.homebank.account.exceptions.CustomerAlreadyExistsException;
import com.homebank.account.exceptions.ResourceNotFoundException;
import com.homebank.account.mapper.AccountsMapper;
import com.homebank.account.mapper.CustomerMapper;
import com.homebank.account.model.Account;
import com.homebank.account.model.Customer;
import com.homebank.account.repository.AccountsRepository;
import com.homebank.account.repository.CustomerRepository;
import com.homebank.account.service.IAccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

        List<Account> accounts = List.of(createNewAccountByType(AccountsConstants.CHECKING,customer),
                createNewAccountByType(AccountsConstants.SAVINGS,customer));
        customer.getAccounts().addAll(accounts);
         customerRepository.save(customer);


    }

    @Override
    public CustomerDto getAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber));
         accountsRepository.findById(customer.getId()).orElseThrow(() -> new ResourceNotFoundException("account", "customerId", customer.getId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        List<Account> accounts = customer.getAccounts();
        List<AccountDto> accountDtos = accounts.stream().map(account1 -> AccountsMapper.mapToAccountsDto(account1, new AccountDto())).toList();
        customerDto.getAccountsDto().addAll(accountDtos);
        return customerDto;


    }

    @Override
    public CustomerDto updateAccount(CustomerDto customerDto) {
            Customer customer = customerRepository.findByMobileNumber(customerDto.getMobileNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("customer", "mobile", customerDto.getMobileNumber()));
            CustomerMapper.mapToCustomer(customerDto, customer);
            Customer savedCustomer = customerRepository.save(customer);
        List<Account> accounts = savedCustomer.getAccounts();
        List<AccountDto> accountDtos = accounts.stream().map(account -> AccountsMapper.mapToAccountsDto(account, new AccountDto())).toList();
        CustomerDto savedCustomerDto = CustomerMapper.mapToCustomerDto(savedCustomer, new CustomerDto());
        savedCustomerDto.getAccountsDto().addAll(accountDtos);
        return savedCustomerDto;
    }


    private Account createNewAccountByType(String type, Customer customer){
        Account account = new Account();
        account.setAccountNumber((UUID.randomUUID().toString()));
        account.setBranchAddress(AccountsConstants.ADDRESS);
        account.setAccountType(type);
        account.setCustomer(customer);
        return account;
    }
}
