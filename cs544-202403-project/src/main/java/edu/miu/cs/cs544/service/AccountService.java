package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.service.contract.AccountPayload;
import edu.miu.cs.cs544.service.contract.AttendancePayload;


import java.util.List;

public interface AccountService extends BaseReadWriteService<AccountPayload, Account, Integer> {
    List<String> findAccountsByBalanceCondition();
    List<AttendancePayload> getAttendanceByAccountIdAndStartTimeAndEndTime(Long accountId, String startTime, String endTime);
}
