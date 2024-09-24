package edu.miu.cs.cs544.service.contract;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocationPayLoad implements Serializable {
    private Integer locationId;
    private String name;
    private String description;
    private String locationType;
}
