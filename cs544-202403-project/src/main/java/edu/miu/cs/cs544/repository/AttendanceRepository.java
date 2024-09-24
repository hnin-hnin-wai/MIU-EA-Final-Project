package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends BaseRepository<Attendance,Integer> {

    @Query("SELECT a FROM Attendance a WHERE a.member.memberId = :memberId")
   public List<Attendance> findAllByMemberId(@Param("memberId") Integer memberId);
   public List<Attendance> findAllByScannerScannerCode(Integer scannerCode);

   public Attendance findByAttendanceId(Integer attendanceId);


}
