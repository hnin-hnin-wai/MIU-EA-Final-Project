package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Location;
import edu.miu.cs.cs544.service.contract.LocationPayLoad;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl extends BaseReadWriteServiceImpl<LocationPayLoad, Location, Integer> implements LocationService{
}
