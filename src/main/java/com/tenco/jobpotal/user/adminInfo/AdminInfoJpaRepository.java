package com.tenco.jobpotal.user.adminInfo;

import com.tenco.jobpotal.user.comp.CompUser;
import com.tenco.jobpotal.user.normal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminInfoJpaRepository extends JpaRepository<AdminInfo, Long> {

    // 관리자 로그인 아이디로 사용자를 조회(로그인 중복체크 모두사용 가능)
    @Query("SELECT a FROM AdminInfo a WHERE a.adminLoginId = :adminLoginId ")
    Optional<AdminInfo> findByAdminLoginId(@Param("adminLoginId") String adminLoginId);

    // 아이디, 이름, 이메일로 사용자 조회(중복 체크용)
    @Query("SELECT a FROM AdminInfo a WHERE a.adminLoginId = :adminLoginId OR a.adminName = :adminName OR a.adminEmail = :adminEmail")
    Optional<User> findByUserExists(@Param("adminLoginId") String adminLoginId,
                                    @Param("adminName") String adminName,
                                    @Param("adminEmail") String adminEmail);
}
