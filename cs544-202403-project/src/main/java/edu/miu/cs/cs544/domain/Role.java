package edu.miu.cs.cs544.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    private String roleType;

    @ManyToMany(mappedBy = "roles")
    Collection<Account> defaultAccounts=new ArrayList<>();

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<Member> members;

    public Role(){}

}
