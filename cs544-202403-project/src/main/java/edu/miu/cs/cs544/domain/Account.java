package edu.miu.cs.cs544.domain;

import edu.miu.cs.cs544.domain.constant.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;
    private  String name;
    private  String description;
    private double balance;
    @Enumerated(EnumType.STRING)
    AccountType accountType;
    @ManyToMany
    @JoinTable(
            name="Account_Role",
            joinColumns=@JoinColumn(name="account_id"),
            inverseJoinColumns =@JoinColumn(name="role_id")
    )
    Collection<Role>  roles=new ArrayList<>();

    public Account(){}

}
