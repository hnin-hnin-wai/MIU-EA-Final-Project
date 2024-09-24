package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Location;
import edu.miu.cs.cs544.service.contract.LocationPayLoad;
import ma.glasnost.orika.MapperFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class LocationToLocationPayloadMapper extends BaseMapper<Location, LocationPayLoad> {
    public LocationToLocationPayloadMapper(MapperFactory mapperFactory){
        super(mapperFactory, Location.class, LocationPayLoad.class);
    }
}
