package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Location;
import edu.miu.cs.cs544.domain.constant.AccountType;
import lombok.Data;

import java.io.Serializable;

@Data
public class ScannerPayload implements Serializable {
    private Integer scannerCode;
    private Integer eventId;
    private Location location;
    private String accountType;
}
