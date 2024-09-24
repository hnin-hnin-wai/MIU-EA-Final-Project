package edu.miu.cs.cs544.domain;

import edu.miu.cs.cs544.domain.constant.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
public class Scanner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scannerCode;

    @ManyToOne
    @JoinColumn(name = "location_id")
    Location location;

    @Enumerated(EnumType.STRING)
    AccountType accountType;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="eventId")//not working
//    Collection<Event> events=new ArrayList<>();
      @OneToOne
      @JoinColumn(name="eventId")
      Event event;
    public Scanner(){}
}
