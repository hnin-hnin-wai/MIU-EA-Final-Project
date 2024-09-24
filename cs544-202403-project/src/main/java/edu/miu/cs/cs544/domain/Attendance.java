package edu.miu.cs.cs544.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
public class Attendance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attendanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scannerCode")//need to confirm
    private Scanner scanner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_Id")
    @JsonBackReference
    private Member member;

    private LocalDateTime scanDateTime;

    public Attendance() {
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", scanner=" + scanner +
                ", member=" + member +
                ", scanDateTime=" + scanDateTime +
                '}';
    }

    public Attendance(Member member, Scanner scanner, LocalDateTime scanDateTime) {
        this.setMember(member);
        this.setScanner(scanner);
        this.setScanDateTime(scanDateTime);
    }

}
