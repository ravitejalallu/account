package com.homebank.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerDto {
    @NotBlank(message = "Name cannot be null or empty")
    @Size(min = 5 ,max = 30, message = "The length of the customer Name should be between 5 and 30 characters")
    private String name;
    @NotBlank(message = "email cannot be null or empty")
    @Email(message = "Not a valid email")
    private String email;
    @NotBlank
    private String mobileNumber;
    private List<AccountDto> accountsDto;

    public List<AccountDto>getAccountsDto(){
        if(accountsDto == null){
            accountsDto =  new ArrayList<>() {
            };
        }
        return accountsDto;
    }
}
