package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.repository.MemberRepository;
import org.springframework.stereotype.Service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.service.contract.MemberPayload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl extends BaseReadWriteServiceImpl<MemberPayload, Member, Integer> implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Map<Member, List<Attendance>> calculateAttendanceForMember(Integer memberId) {

        List<Attendance> attendanceList = memberRepository.findAttendanceByMemberId(memberId);
        Map<Member, List<Attendance>> attendanceMap = new HashMap<>();

        // Group attendance by member
        for (Attendance attendance : attendanceList) {
            // Assuming attendance has a reference to Event
            Event event = attendance.getScanner().getEvent();

            // Assuming event has a reference to Member
            Member member = event.getMembers().iterator().next();

            if (!attendanceMap.containsKey(member)) {
                attendanceMap.put(member, new ArrayList<>());
            }

            attendanceMap.get(member).add(attendance);
        }

        return attendanceMap;
    }

//    private final MemberRepository memberRepository;
//    private final RoleRepository roleRepository;
//
//
//    @Override
//    public MemberRolesResponseDTO getRoles(long memberId) throws MemberNotFoundException {
//        final var member = memberRepository.findById(memberId).orElseThrow(
//                () -> new MemberNotFoundException(memberId)
//        );
//        return new MemberRolesResponseDTO(
//                memberId,
//                List.copyOf(member.getRole())
//        );
//    }
//
//    @Transactional
//    @Override
//    public ModifyMemberRoleResponseDTO assignRole(long roleId, long memberId) throws MemberNotFoundException, RoleNotFoundException {
//        final var member = memberRepository.findById(memberId).orElseThrow(
//                () -> new MemberNotFoundException(memberId)
//        );
//        final var role = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException(roleId));
//        final var memberRoles = member.getRole();
//        final var memberAccounts = member.getAccounts();
//        final var supportedAccountTypes = memberAccounts.stream().map(Account::getType).collect(Collectors.toSet());
//        final var notYetSupportedAccountTypes = new HashSet<>(role.getDefaultAccountTypes());
//        notYetSupportedAccountTypes.removeAll(supportedAccountTypes);
//        memberRoles.add(role);
//        for (final var accountType : notYetSupportedAccountTypes) {
//            memberAccounts.add(
//                    new Account(
//                            "", "", accountType
//                    )
//            );
//        }
//        memberRepository.save(member);
//        return new ModifyMemberRoleResponseDTO(
//                memberId,
//                List.copyOf(member.getRole()),
//                List.copyOf(memberAccounts)
//        );
//    }
//
//    @Transactional
//    @Override
//    public ModifyMemberRoleResponseDTO removeRole(long roleId, long memberId) throws MemberNotFoundException, RoleNotFoundException {
//        final var member = memberRepository.findById(memberId).orElseThrow(
//                () -> new MemberNotFoundException(memberId)
//        );
//        final var role = roleRepository.findById(roleId).orElseThrow(
//                () -> new RoleNotFoundException(roleId)
//        );
//        member.getRole().remove(role);
//        memberRepository.save(member);
//        return new ModifyMemberRoleResponseDTO(
//                memberId,
//                List.copyOf(member.getRole()),
//                List.copyOf(member.getAccounts())
//        );
//    }

}
