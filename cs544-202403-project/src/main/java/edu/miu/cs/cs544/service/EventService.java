package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.service.contract.EventPayload;

import java.util.List;
import java.util.Map;

public interface EventService extends BaseReadWriteService<EventPayload, Event, Integer> {
    Map<Member, List<Attendance>> calculateAttendance(Integer eventId);
    Event getByEventId(Integer eventId);

}
