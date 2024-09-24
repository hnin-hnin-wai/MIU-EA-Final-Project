package edu.miu.cs.cs544.controller;


import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.service.contract.MemberPayload;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/members")
public class MemberController extends BaseReadWriteController<MemberPayload, Member, Integer> {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/{memberId}/attendance")
    public ResponseEntity<Map<Member, List<Attendance>>> calculateAttendance(@PathVariable Integer memberId) {
        Map<Member, List<Attendance>> attendanceMap = memberService.calculateAttendanceForMember(memberId);
        return ResponseEntity.ok(attendanceMap);
    }
}
