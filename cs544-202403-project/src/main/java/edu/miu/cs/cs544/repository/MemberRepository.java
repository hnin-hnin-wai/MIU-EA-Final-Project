package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends BaseRepository<Member, Integer>{
    @Query("SELECT a FROM Attendance a WHERE a.scanner.event IN (SELECT e FROM Member m JOIN m.events e WHERE m.memberId = :memberId)")
    List<Attendance> findAttendanceByMemberId(@Param("memberId") Integer memberId);


}
