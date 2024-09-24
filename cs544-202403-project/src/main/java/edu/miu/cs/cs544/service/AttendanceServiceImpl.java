package edu.miu.cs.cs544.service;

import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Scanner;
import edu.miu.cs.cs544.domain.constant.AccountType;
import edu.miu.cs.cs544.repository.AttendanceRepository;
import edu.miu.cs.cs544.repository.MemberRepository;
import edu.miu.cs.cs544.repository.ScannerRepository;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import edu.miu.cs.cs544.service.mapper.AttendanceToAttendancePayloadMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttendanceServiceImpl extends BaseReadWriteServiceImpl<AttendancePayload, Attendance,Integer> implements AttendanceService {

    @Autowired
    private final AttendanceRepository attendanceRepository;

    @Autowired
    private final ScannerRepository scannerRepository;

    private final MemberRepository memberRepository;

    @Autowired
    private AttendanceToAttendancePayloadMapper attendanceToAttendancePayloadMapper;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, ScannerRepository scannerRepository, MemberRepository memberRepository,AttendanceToAttendancePayloadMapper attendanceToAttendancePayloadMapper) {
        this.attendanceRepository = attendanceRepository;
        this.scannerRepository = scannerRepository;
        this.memberRepository = memberRepository;
        this.attendanceToAttendancePayloadMapper=attendanceToAttendancePayloadMapper;
    }

    @Override
    public List<AttendancePayload> findByScannerCode(Integer scannerCode) {


        List<Attendance> scanRecords = attendanceRepository.findAllByScannerScannerCode(scannerCode);

        return scanRecords.stream()
                .map(record -> attendanceToAttendancePayloadMapper.customMapping(record))
                .collect(Collectors.toList());


        //return scanRecords.stream().map(record-> attendanceToAttendancePayloadMapper.map(record)).toList();
       // return attendanceToAttendancePayloadMapper.map(scanRecords.get(0));
        //result.add(attendanceToAttendancePayloadMapper.map(scanRecords.get(0)));
//        for (Attendance attendance : scanRecords) {
//            result.add(attendanceToAttendancePayloadMapper.map(attendance));
//            //attendanceToAttendancePayloadMapper.map(attendance);
//
//        }
       // return result;
//        return scanRecords.stream().map(attendanceToAttendancePayloadMapper::map).toList();
    }


    public void saveAttendance(Integer scannerCode, AttendancePayload attendancePayload) {
        Scanner scanner = scannerRepository.findById(scannerCode)
                .orElseThrow(() -> new ResourceNotFoundException("Scanner not found with id: " + scannerCode));
        Member member = memberRepository.findById(attendancePayload.getMember().getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + attendancePayload.getMember().getMemberId()));

        LocalDateTime scanDateTime = LocalDateTime.now();
        Attendance attendance = new Attendance(member, scanner, scanDateTime);
        //recordMapper.map(recordRepository.save(record));
        attendanceRepository.save(attendance);
//        attendanceToAttendancePayloadMapper.map(attendanceRepository.save(attendance));
    }

    @Override
    public void deleteAttendance(Integer scannerCode, Integer recordId) {

        Attendance attendance = attendanceRepository.findByAttendanceId(recordId);
        if (!attendance.getScanner().getScannerCode().equals(scannerCode)) {
            throw new ResourceNotFoundException("Record with id: " + recordId + " does not belong to Scanner with id: " + scannerCode);

        }
        attendanceRepository.delete(attendance);

    }

    @Override
    public AttendancePayload updateAttendance(Integer scannerCode, Integer recordId, AttendancePayload attendancePayload) {
        Attendance attendance = attendanceRepository.findByAttendanceId(recordId);


        if (!attendance.getScanner().getScannerCode().equals(scannerCode)) {
            throw new ResourceNotFoundException("Record with id: " + recordId + " does not belong to Scanner with id: " + scannerCode);
        }

        attendance.setMember(attendancePayload.getMember());
        attendance = attendanceRepository.save(attendance);

        return attendanceToAttendancePayloadMapper.map(attendance);
    }

}
