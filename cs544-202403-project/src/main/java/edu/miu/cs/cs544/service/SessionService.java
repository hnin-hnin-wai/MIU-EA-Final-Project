package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.contract.MemberPayload;
import edu.miu.cs.cs544.service.contract.SessionPayload;

import java.util.List;


public interface SessionService extends BaseReadWriteService<SessionPayload, Session, Integer>{
    void addSession(Integer eventId, SessionPayload session);

    //add session
    void updateSession(Integer eventId, SessionPayload session);

    void deleteSession(Integer eventId, Integer sessionId);

    SessionPayload getSession(Integer eventId, Integer sessionId);

    List<SessionPayload> getAllSession(Integer eventId);
}
