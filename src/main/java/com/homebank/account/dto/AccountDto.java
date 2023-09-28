package com.homebank.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold  Account information"
)
public class AccountDto {

    @Schema(
            description = "Account Number of Home Bank account", example = "3454433243"
    )
    private String accountNumber;
    @Schema(
            description = "Account type of Home Bank account", example = "Savings"
    )
    private String accountType;
    @Schema(
            description = "Home Bank branch address", example = "123 NewYork"
    )
    private String branchAddress;
}
