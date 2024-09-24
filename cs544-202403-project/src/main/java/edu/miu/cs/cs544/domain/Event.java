package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private  Integer eventId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private  String description;
    @Column(name = "START_DATE_TIME", nullable = false)
    private LocalDateTime startedDateTime;
    @Column(name = "END_DATE_TIME", nullable = false)
    private  LocalDateTime endedDateTime;

//    @OneToMany(cascade = CascadeType.PERSIST)
//    @JoinColumn(name="eventId")
//    private List<Session> sessions = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="event_id")
    private List<Session> schedule;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="Event_Member",
            joinColumns=@JoinColumn(name="event_id"),
            inverseJoinColumns =@JoinColumn(name="member_id")
    )
    private Collection<Member> members=new ArrayList<>();

    public Event(){}
}
