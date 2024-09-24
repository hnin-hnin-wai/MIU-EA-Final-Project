package edu.miu.cs.cs544.controller;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.service.EventService;
import edu.miu.cs.cs544.service.contract.EventPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("events")
public class EventController extends BaseReadWriteController<EventPayload, Event, Integer> {

    private final EventService eventService;
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{eventId}/attendance")
    public ResponseEntity<Map<Member, List<Attendance>>> calculateAttendance(@PathVariable Integer eventId) {
        Map<Member, List<Attendance>> attendanceMap = eventService.calculateAttendance(eventId);
        return ResponseEntity.ok(attendanceMap);
    }
}