package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Role;
import edu.miu.cs.cs544.domain.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends BaseRepository<Role, Integer>{


    @Query("select r from Member m join m.roles r where m.memberId =:memberId and r.roleId =:roleId")
    Role findByMemberIdAndRoleId(@Param("memberId")Integer memberId, @Param("roleId") Integer roleId);
}
