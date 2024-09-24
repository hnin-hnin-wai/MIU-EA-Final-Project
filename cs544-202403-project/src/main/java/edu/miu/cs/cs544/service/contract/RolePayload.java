package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.domain.Member;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class RolePayload implements Serializable {

    private Integer roleId;
    private String roleType;

    Collection<Account> defaultAccounts=new ArrayList<>();

    Collection<Member> members=new ArrayList<>();

}
