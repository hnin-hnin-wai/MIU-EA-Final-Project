package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Location;
import edu.miu.cs.cs544.service.contract.LocationPayLoad;

public interface LocationService extends BaseReadWriteService<LocationPayLoad, Location, Integer> {
}
