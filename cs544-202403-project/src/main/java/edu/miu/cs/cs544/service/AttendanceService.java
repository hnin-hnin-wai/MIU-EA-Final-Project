package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.service.contract.AttendancePayload;

import java.util.List;
import java.util.Map;

public interface AttendanceService extends BaseReadWriteService<AttendancePayload, Attendance,Integer> {
    List<AttendancePayload> findByScannerCode(Integer scannerCode);

    public void saveAttendance(Integer scannerCode, AttendancePayload attendancePayload);

    public void deleteAttendance(Integer scannerCode,Integer recordId);

    public AttendancePayload updateAttendance(Integer scannerCode,Integer recordId,AttendancePayload attendancePayload);

}
