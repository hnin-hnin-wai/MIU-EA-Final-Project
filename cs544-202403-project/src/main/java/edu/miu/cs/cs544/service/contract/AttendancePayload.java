package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Scanner;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AttendancePayload implements Serializable {
    private Integer attendanceId;
    private Integer memberId;
    private Integer scannerId;
    private Member member;
    private Scanner scannerCode;
    private LocalDateTime scanDateTime;

}
