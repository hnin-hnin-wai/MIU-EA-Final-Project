package edu.miu.cs.cs544.service.contract;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SessionPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer sessionId;

    private LocalDateTime startedDateTime;

    private LocalDateTime endedDateTime;

    private Integer numOfSessions;

}
