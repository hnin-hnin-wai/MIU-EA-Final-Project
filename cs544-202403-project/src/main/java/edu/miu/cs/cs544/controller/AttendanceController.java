package edu.miu.cs.cs544.controller;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.service.AttendanceService;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scannerRecords")
public class AttendanceController extends BaseReadWriteController<AttendancePayload, Attendance,Integer> {
    private final AttendanceService attendanceService;
    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/{scannerCode}/records")
    public ResponseEntity<?> getScanRecordsByScannerCode(@PathVariable Integer scannerCode){
       List<AttendancePayload> records=attendanceService.findByScannerCode(scannerCode);
       if(records.isEmpty()) {
           throw new ResourceNotFoundException("No record found in this Scanner");
       }
       return  new ResponseEntity<List<AttendancePayload>>(records, HttpStatus.OK);
    }
    @PostMapping("/{scannerCode}/records")
    @ResponseStatus(HttpStatus.OK)
    public void saveScanRecords(@PathVariable Integer scannerCode,@RequestBody AttendancePayload attendancePayload){
        attendanceService.saveAttendance(scannerCode,attendancePayload);
    }

    @DeleteMapping("/{scannerCode}/records/{recordId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteScanRecords(@PathVariable Integer scannerCode,@PathVariable Integer recordId ){
        attendanceService.deleteAttendance(scannerCode,recordId);
    }

    @PutMapping("/{scannerCode}/records/{recordId}")
    @ResponseStatus(HttpStatus.OK)
    public AttendancePayload updateScanRecords(@PathVariable Integer scannerCode,@PathVariable Integer recordId,@RequestBody AttendancePayload attendancePayload){
       return attendanceService.updateAttendance(scannerCode,recordId,attendancePayload);
    }


}
