package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Role;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.repository.MemberRepository;
import edu.miu.cs.cs544.repository.RoleRepository;
import edu.miu.cs.cs544.repository.SessionRepository;
import edu.miu.cs.cs544.service.contract.RolePayload;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import edu.miu.cs.cs544.service.mapper.RolePayloadToRoleMapper;
import edu.miu.cs.cs544.service.mapper.RoleToRolePayloadMapper;
import edu.miu.cs.cs544.service.mapper.SessionPayloadToSessionMapper;
import edu.miu.cs.cs544.service.mapper.SessionToSessionPayloadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RoleServiceImpl extends BaseReadWriteServiceImpl<RolePayload, Role, Integer> implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RolePayloadToRoleMapper toRoleMapper;

    @Autowired
    private RoleToRolePayloadMapper toRolePayloadMapper;

    public Member getMember(Integer memberId){
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    @Transactional
    public void addRole(Integer memberId, RolePayload rolePayload) {
        Member member1 = getMember(memberId);
        if(member1 != null){
            Role role1 = toRoleMapper.map(rolePayload);
            role1 =  repository.save(role1);
            member1.getRoles().add(role1);
            memberRepository.save(member1);
        }
    }


    @Override
    @Transactional
    public void updateRole(Integer memberId, RolePayload rolePayload) {
        Member member1 = getMember(memberId);
        if(member1 != null){
            Role role1 = toRoleMapper.map(rolePayload);
            member1.getRoles().removeIf(role2 -> Objects.equals(role2.getRoleId(), role1.getRoleId()));
            repository.deleteById(role1.getRoleId());
            Role role2 =  repository.save(role1);
            member1.getRoles().add(role2);
            memberRepository.save(member1);
        }
    }

    @Override
    @Transactional
    public void deleteRole(Integer memberId, Integer roleId) {
        Member member1 = getMember(memberId);
        if(member1 != null){
           member1.getRoles().removeIf(role -> Objects.equals(role.getRoleId(), roleId));
           memberRepository.save(member1);
           repository.deleteById(roleId);
        }
    }

    @Override
    public RolePayload getRole(Integer memberId, Integer roleId) {
        Member member1 = getMember(memberId);
        Role role = repository.findByMemberIdAndRoleId(memberId,roleId);
        if(role != null){
            return toRolePayloadMapper.map(role);
        }
        return null;
    }

    @Override
    public List<RolePayload> getAllRoles(Integer memberId) {
        Member member1 = getMember(memberId);
        Set<Role> roles = new HashSet<>();
        List<RolePayload> rolePayloads = new ArrayList<>();
        if(member1 != null){
            roles = member1.getRoles();
            if(!roles.isEmpty()){
                for(Role role : roles){
                    rolePayloads.add(toRolePayloadMapper.map(role));
                }
            }
        }
        return rolePayloads;
    }
}
