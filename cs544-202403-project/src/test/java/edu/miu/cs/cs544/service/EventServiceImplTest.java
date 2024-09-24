package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.repository.AttendanceRepository;
import edu.miu.cs.cs544.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {
    @Mock
    private EventRepository eventRepository;

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCalculateAttendance_EventNotFound() {
        // Given
        Integer eventId = 1;

        // Mocking behavior of eventRepository.findById() to return empty optional
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        // When
        Map<Member, List<Attendance>> attendanceMap = eventService.calculateAttendance(eventId);

        // Then
        assertEquals(0, attendanceMap.size());
    }

    @Test
    void testCalculateAttendance_EventFound() {
        // Given
        Integer eventId = 1;
        Event event = new Event();
        event.setEventId(eventId);

        Member member = new Member();
        member.setMemberId(1);

        Attendance attendance = new Attendance();
        attendance.setMember(member);
        List<Attendance> attendanceList = new ArrayList<>();
        attendanceList.add(attendance);

        // Mocking behavior of eventRepository.findById() and attendanceRepository.findAllByMemberId()
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(attendanceRepository.findAllByMemberId(member.getMemberId())).thenReturn(attendanceList);

        // When
        Map<Member, List<Attendance>> attendanceMap = eventService.calculateAttendance(eventId);

        // Then
        assertEquals(1, attendanceMap.size());
    }

}