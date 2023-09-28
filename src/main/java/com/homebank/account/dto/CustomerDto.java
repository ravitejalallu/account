package com.homebank.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold  Customer  information"
)
public class CustomerDto {
    @Schema(
            description = "Name of the customer", example = "customer"
    )
    @NotBlank(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer Name should be between 5 and 30 characters")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "email@email.com"
    )
    @NotBlank(message = "email cannot be null or empty")
    @Email(message = "Not a valid email")
    private String email;
    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
    @NotBlank
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private List<AccountDto> accountsDto;

    public List<AccountDto> getAccountsDto() {
        if (accountsDto == null) {
            accountsDto = new ArrayList<>() {
            };
        }
        return accountsDto;
    }
}
