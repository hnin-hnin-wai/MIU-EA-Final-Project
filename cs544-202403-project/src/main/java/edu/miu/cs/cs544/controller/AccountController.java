package edu.miu.cs.cs544.controller;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.service.AccountService;
import edu.miu.cs.cs544.service.contract.AccountPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/accounts")
public class AccountController extends BaseReadWriteController<AccountPayload, Account, Integer> {

    @Autowired
    AccountService accountService;

//    @Autowired
//    private ScanRecordPayload scanRecordPayload;

    @GetMapping("/{accountId}/attendace/{startTime}/{endTime}")
    public ResponseEntity<?> getAttendaceByAccountIdAndTime(@PathVariable Integer accountId, @PathVariable String startTime, @PathVariable String endTime) {
//
//        List<scanRecordPayload> attendacePayloadList = accountService.getAttendanceByAccountIdAndStartTimeAndEndTime(accountId, startTime, endTime);
//        Map<Long, List<scanRecordPayload>> map = new HashMap<Long, List<scanRecordPayload>>();
//        map.put(accountId, attendacePayloadList);
//        return new ResponseEntity<>(map, HttpStatus.OK);
        return null;
    }
    

}
