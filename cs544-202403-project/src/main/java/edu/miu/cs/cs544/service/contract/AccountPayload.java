package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.constant.AccountType;
import lombok.Data;

import java.io.Serializable;

@Data
public class AccountPayload implements Serializable {
    private Integer accountId;
    private  String name;
    private  String description;
    private double balance;
    private double defaultBalance = 100;
    private AccountType type;



    public AccountPayload(String name, String description, double defaultBalance, double balance, AccountType type) {
        this.name = name;
        this.description = description;
        this.defaultBalance = defaultBalance;
        this.balance = balance;
        this.type = type;
    }
}
