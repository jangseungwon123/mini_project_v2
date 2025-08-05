package com.tenco.jobpotal.user.adminInfo;

import com.tenco.jobpotal.user.comp.CompUser;
import com.tenco.jobpotal.user.normal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminInfoJpaRepository extends JpaRepository<AdminInfo, Long> {

    // 관리자 로그인 아이디와 비밀번호로 사용자 조회(로그인)
    @Query("select cu from CompUser cu where cu.compUserLoginId = :compUserLoginId and cu.compUserPassword = :compUserPassword")
    Optional<CompUser> findByAdminInfoLoginIdAndAdminInfoPassword(@Param("compUserLoginId") String compUserLoginId,
                                                                @Param("compUserPassword") String compUserPassword);

    // 관리자 회원 로그인 아이디로 사용자 조회(중복체크)
    @Query("select cu from CompUser cu where cu.compUserLoginId = :compUserLoginId or cu.compUserEmail = :compUserEmail")
    Optional<CompUser> findByAdminInfoExists(@Param("AdminInfoLoginId") String AdminInfoLoginId,
                                            @Param("AdminInfoEmail") String AdminInfoEmail);

}
