package edu.miu.cs.cs544.controller;


import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Role;
import edu.miu.cs.cs544.service.EventService;
import edu.miu.cs.cs544.service.MemberService;
import edu.miu.cs.cs544.service.RoleService;
import edu.miu.cs.cs544.service.SessionService;
import edu.miu.cs.cs544.service.contract.MemberPayload;
import edu.miu.cs.cs544.service.contract.RolePayload;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/members/{memberId}/roles")
@RestController
public class RoleController extends BaseReadWriteController<RolePayload, Role, Integer> {

    @Autowired
    private RoleService roleService;


    @Autowired
    private MemberService memberService;



    @GetMapping("/id/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public RolePayload getRole(@PathVariable("memberId") Integer memberId, @PathVariable("roleId") Integer roleId) {
        return roleService.getRole(memberId, roleId);
    }

    @PostMapping("/+")
    @ResponseStatus(HttpStatus.OK)
    public void addRole(@PathVariable("memberId") Integer memberId, @RequestBody RolePayload rolePayload) {
        roleService.addRole(memberId, rolePayload);
    }

    @PutMapping("/+-")
    @ResponseStatus(HttpStatus.OK)
    public void updateRole(@PathVariable("memberId") Integer memberId, @RequestBody RolePayload rolePayload) {
        roleService.updateRole(memberId, rolePayload);
    }

    @DeleteMapping("/id/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRole(@PathVariable("memberId") Integer memberId, @PathVariable("roleId") Integer roleId) {
        roleService.deleteRole(memberId, roleId);
    }

    @GetMapping("/a")
    @ResponseStatus(HttpStatus.OK)
    public List<RolePayload> getAllRoles(@PathVariable("memberId") Integer memberId) {
        return roleService.getAllRoles(memberId);
    }
}
