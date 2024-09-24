package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Role;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.contract.RolePayload;
import edu.miu.cs.cs544.service.contract.SessionPayload;

import java.util.List;


public interface RoleService extends BaseReadWriteService <RolePayload, Role, Integer>{
    void addRole(Integer memberId, RolePayload rolePayload);
//    void addRole(Integer memberId, Integer roleId);

    //add session
    void updateRole(Integer memberId, RolePayload rolePayload);

    void deleteRole(Integer memberId, Integer roleId);

    RolePayload getRole(Integer memberId, Integer roleId);

    List<RolePayload> getAllRoles(Integer memberId);
}
