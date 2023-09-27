package com.homebank.account.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts  extends  BaseEntity {
    @Column
    private Long customerId;
    @Id
    @Column
    private String accountNumber;
    @Column
    private String accountType;
    @Column
    private String branchAddress;
}
