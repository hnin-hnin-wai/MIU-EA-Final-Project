package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Role;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.contract.RolePayload;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class RolePayloadToRoleMapper extends BaseMapper<RolePayload, Role>{

	public RolePayloadToRoleMapper(MapperFactory mapperFactory) {
		super(mapperFactory, RolePayload.class, Role.class);
	}

}
