package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.repository.AttendanceRepository;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.service.contract.EventPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventServiceImpl extends BaseReadWriteServiceImpl<EventPayload, Event, Integer> implements EventService{
    private final EventRepository eventRepository;
    private final AttendanceRepository attendanceRepository;

    public EventServiceImpl(EventRepository eventRepository, AttendanceRepository attendanceRepository) {
        this.eventRepository = eventRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Map<Member, List<Attendance>> calculateAttendance(Integer eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);

        if (event == null) {
            // Return an empty map if the event is not found
            return new HashMap<>();
        }

        Map<Member, List<Attendance>> attendanceMap = new HashMap<>();

        for (Member member : event.getMembers()) {
            List<Attendance> memberAttendance = attendanceRepository.findAllByMemberId(member.getMemberId());
            attendanceMap.put(member, memberAttendance);
        }

        return attendanceMap;
    }
    @Override
    public Event getByEventId(Integer eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        return eventOptional.orElse(null);
    }

}
