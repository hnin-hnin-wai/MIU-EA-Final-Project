package edu.miu.cs.cs544.service.contract;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EventPayload implements Serializable {
    private Integer eventId;
    private String name;
    private  String description;
    private LocalDateTime startedDateTime;
    private  LocalDateTime endedDateTime;

    public EventPayload(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

