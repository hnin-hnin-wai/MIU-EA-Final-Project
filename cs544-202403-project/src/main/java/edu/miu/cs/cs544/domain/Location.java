package edu.miu.cs.cs544.domain;

import edu.miu.cs.cs544.domain.constant.LocationType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="location_id")
    private Integer locationId;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    LocationType locationType;

    public Location(){}


}
