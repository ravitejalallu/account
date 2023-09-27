package com.homebank.account.controller;

import com.homebank.account.constants.AccountsConstants;
import com.homebank.account.dto.CustomerDto;
import com.homebank.account.dto.ResponseDto;
import com.homebank.account.service.IAccountService;
import com.homebank.account.service.impl.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1",produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {

    private final IAccountService accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto>createAccount(@RequestBody CustomerDto customerDto){
     accountService.createAccount(customerDto);
      return ResponseEntity.status(HttpStatus.CREATED)
              .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/accounts")
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam String mobileNumber){
        return new ResponseEntity<>(accountService.getAccount(mobileNumber),HttpStatus.OK);
    }
}
