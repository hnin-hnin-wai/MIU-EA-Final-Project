package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class AttendanceToAttendancePayloadMapper extends BaseMapper<Attendance, AttendancePayload> {
    public AttendanceToAttendancePayloadMapper(MapperFactory mapperFactory){
        super(mapperFactory, Attendance.class, AttendancePayload.class);
    }

    public AttendancePayload customMapping(Attendance source) {
        AttendancePayload target = new AttendancePayload();
        target.setAttendanceId(source.getAttendanceId());
        target.setMemberId(source.getMember().getMemberId());
        target.setScannerId(source.getScanner().getScannerCode());

//        target.setMember(source.getMember());
//        target.setScannerCode(source.getScanner());

        target.setScanDateTime(source.getScanDateTime());
        return target;
    }
}
