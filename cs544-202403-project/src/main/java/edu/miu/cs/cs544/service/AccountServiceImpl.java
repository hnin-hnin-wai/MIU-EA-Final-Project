package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.repository.AccountRepository;
import edu.miu.cs.cs544.repository.AttendanceRepository;
import edu.miu.cs.cs544.service.contract.AccountPayload;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountServiceImpl extends BaseReadWriteServiceImpl<AccountPayload, Account, Integer> implements AccountService{
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Override
    public List<String> findAccountsByBalanceCondition() {
        return null;
    }

    @Override
    public List<AttendancePayload> getAttendanceByAccountIdAndStartTimeAndEndTime(Long accountId, String startTime, String endTime) {
        return null;
    }

//    @Override
//    public List<String> findAccountsByBalanceCondition() {
//        return accountRepository.findAccountsByBalanceCondition();
//    }

//    @Override
//    public List<AttendancePayload> getAttendanceByAccountIdAndStartTimeAndEndTime(Long accountId, String startTime, String endTime) {
//        return null;
//    }

//    @Override
//    public List<AttendancePayload> getAttendanceByAccountIdAndStartTimeAndEndTime(Long accountId, String startTime, String endTime) {
//        return null;
//    }

//    public List<AttendancePayload> getAttendanceByAccountIdAndStartTimeAndEndTime(Long accountId, String startTime, String endTime) {
//        List<Attendance> attendanceList = attendanceRepository.findAllByAccountId(accountId, startTime, endTime);
//        List<AttendancePayload> attendancePayloadList = new ArrayList<AttendancePayload>();
//        attendanceList.stream().forEach(a -> {
//            AttendancePayload ap = new AttendancePayload();
//            ap.setMember(a.getMember());
//            ap.setScanner(a.getScanner());
//            attendancePayloadList.add(ap);
//        });
//
//        return attendancePayloadList;
//
//    }
}
