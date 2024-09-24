package edu.miu.cs.cs544.service;

import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Scanner;
import edu.miu.cs.cs544.repository.AttendanceRepository;
import edu.miu.cs.cs544.repository.MemberRepository;
import edu.miu.cs.cs544.repository.ScannerRepository;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import edu.miu.cs.cs544.service.mapper.AttendanceToAttendancePayloadMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AttendanceServiceImplTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private ScannerRepository scannerRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private AttendanceToAttendancePayloadMapper attendanceToAttendancePayloadMapper;

    @InjectMocks
    private AttendanceServiceImpl attendanceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByScannerCode() {
        // Given
        int scannerCode = 123;
        Attendance attendance1 = new Attendance(new Member(), new Scanner(), LocalDateTime.now());
        Attendance attendance2 = new Attendance(new Member(), new Scanner(), LocalDateTime.now());
        List<Attendance> attendanceList = Arrays.asList(attendance1, attendance2);
        when(attendanceRepository.findAllByScannerScannerCode(scannerCode)).thenReturn(attendanceList);

        // When
        List<AttendancePayload> result = attendanceService.findByScannerCode(scannerCode);

        // Then
        assertEquals(2, result.size());
        // Add more assertions if needed
    }

    @Test
    void testSaveAttendance() {
        // Given
        int scannerCode = 123;
        AttendancePayload attendancePayload = new AttendancePayload();
        attendancePayload.setMember(new Member());

        Scanner scanner = new Scanner();
        Member member = new Member();
        when(scannerRepository.findById(scannerCode)).thenReturn(Optional.of(scanner));
        when(memberRepository.findById(attendancePayload.getMember().getMemberId())).thenReturn(Optional.of(member));

        // When
        attendanceService.saveAttendance(scannerCode, attendancePayload);

        // Then
        verify(attendanceRepository, times(1)).save(any(Attendance.class));
    }

//    @Test
//    void testUpdateAttendance_Success() {
//        // Given
//        int scannerCode = 123;
//        int recordId = 456;
//        AttendancePayload attendancePayload = new AttendancePayload();
//        Member member = new Member();
//        attendancePayload.setMember(member);
//
//        Scanner scanner = new Scanner();
//        Attendance attendance = new Attendance(member, scanner, LocalDateTime.now());
//        when(attendanceRepository.findByAttendanceId(recordId)).thenReturn(attendance);
//        when(scannerRepository.findById(scannerCode)).thenReturn(Optional.of(scanner));
//
//        // When
//        AttendancePayload result = attendanceService.updateAttendance(scannerCode, recordId, attendancePayload);
//
//        // Then
//        verify(attendanceRepository, times(1)).save(attendance);
//        assertEquals(attendancePayload, result);
//    }
//
//    @Test
//    void testDeleteAttendance() {
//        // Given
//        int scannerCode = 123;
//        int recordId = 456;
//        Attendance attendance = new Attendance(new Member(), new Scanner(), LocalDateTime.now());
//        when(attendanceRepository.findByAttendanceId(recordId)).thenReturn(attendance);
//
//        // When
//        attendanceService.deleteAttendance(scannerCode, recordId);
//
//        // Then
//        verify(attendanceRepository, times(1)).delete(attendance);
//    }

}