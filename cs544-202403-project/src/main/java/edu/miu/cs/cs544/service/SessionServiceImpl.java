package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.repository.SessionRepository;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.miu.cs.cs544.service.mapper.SessionToSessionPayloadMapper;
import edu.miu.cs.cs544.service.mapper.SessionPayloadToSessionMapper;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl extends BaseReadWriteServiceImpl<SessionPayload, Session, Integer> implements SessionService {

    @Autowired
    private SessionRepository repository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SessionPayloadToSessionMapper toSessionMapper;

    @Autowired
    private SessionToSessionPayloadMapper toSessionPayloadMapper;

    private Event event;

    public Event getEvent(Integer eventId){
        return eventRepository.findById(eventId).orElse(null);
    }

    @Override
    @Transactional
    public void addSession(Integer eventId, SessionPayload session) {
        Event event = getEvent(eventId);
        if(event != null){
            Session session1 = toSessionMapper.map(session);
            event.getSchedule().add(session1);
            eventRepository.save(event);
//            repository.save(session1);
        }
    }

    @Override
    @Transactional
    public void updateSession(Integer eventId, SessionPayload session) {
        Event event = getEvent(eventId);
        if(event != null){
            Session session1 = toSessionMapper.map(session);
            event.getSchedule().removeIf(session2 -> session2.getSessionId() == session1.getSessionId());
            event.getSchedule().add(session1);
            eventRepository.save(event);
//            repository.save(session1);
        }
    }

    @Override
    @Transactional
    public void deleteSession(Integer eventId, Integer sessionId) {
        Event event = getEvent(eventId);
        if(event != null){
           List<Session> sessions = event.getSchedule();
           boolean isDelelte = sessions.removeIf(session2 -> session2.getSessionId() == sessionId);
           eventRepository.save(event);
           repository.deleteById(sessionId);
        }
    }

    @Override
    public SessionPayload getSession(Integer eventId, Integer sessionId) {
        Event event1 = getEvent(eventId);
        List<Session> sessions = repository.findAllByEventIdAndSessionId(eventId,sessionId);
        for(Session session : sessions){
            if(session.getSessionId() == sessionId){
                return toSessionPayloadMapper.map(session);
            }
        }
        return null;
    }

    @Override
    public List<SessionPayload> getAllSession(Integer eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        List<Session> sessions = new ArrayList<>();
        List<SessionPayload> sessionPayloads = new ArrayList<>();
        if(eventOptional.isPresent()){
            sessions  = eventOptional.get().getSchedule();
        }
        if(!sessions.isEmpty()){
            for(Session session : sessions){
                sessionPayloads.add(toSessionPayloadMapper.map(session));
            }
        }
        return sessionPayloads;
    }
}
