package edu.miu.cs.cs544.controller;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.EventService;
import edu.miu.cs.cs544.service.SessionService;
import edu.miu.cs.cs544.service.contract.MemberPayload;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sessions")
@RestController
public class SessionController extends BaseReadWriteController<SessionPayload, Session, Integer> {

    @Autowired
   private SessionService sessionService;

    @Autowired
    private EventService eventService;


    @GetMapping("/id/{sessionId}")
    @ResponseStatus(HttpStatus.OK)
    public SessionPayload getSession(@PathVariable("eventId") Integer eventId,@PathVariable("sessionId") Integer sessionId){
       return  sessionService.getSession(eventId,sessionId);
    }

    @PostMapping("/+")
    @ResponseStatus(HttpStatus.OK)
    public void addSession(@PathVariable("eventId") Integer eventId,@RequestBody SessionPayload session){
        sessionService.addSession(eventId,session);
    }

    @PutMapping("/+-")
    @ResponseStatus(HttpStatus.OK)
    public void updateSession(@PathVariable("eventId") Integer eventId, @RequestBody SessionPayload session){
        sessionService.updateSession(eventId,session);
    }

    @DeleteMapping("/id/{sessionId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSession(@PathVariable("eventId") Integer eventId, @PathVariable Integer sessionId){
        sessionService.deleteSession(eventId,sessionId);
    }

    @GetMapping("/a")
    @ResponseStatus(HttpStatus.OK)
    public List<SessionPayload> getAllSessions(@PathVariable("eventId") Integer eventId){
       return sessionService.getAllSession(eventId);
    }

}
