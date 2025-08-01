package com.tenco.jobpotal.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    // 아이디와 비밀번호로 사용자 조회 (로그인용)
    @Query("SELECT u FROM User u WHERE u.userLoginId = :userLoginId AND u.userPassword = :userPassword")
    Optional<User> findByUserIdAndPassword(@Param("userLoginId") String userLoginId,
                                           @Param("userPassword") String userPassword);

    // 아이디, 주민번호, 이메일로 사용자 조회(중복 체크용)
    @Query("SELECT u FROM User u WHERE u.userLoginId = :userLoginId OR u.userCivilSerial = :userCivilSerial OR u.userEmail = :userEmail")
    Optional<User> findByUserExists(@Param("userLoginId") String userLoginId,
                                    @Param("userCivilSerial") String userCivilSerial,
                                    @Param("userEmail") String userEmail);

    // 아이디로 사용자 조회(회원 정보 조회용)
    @Query("SELECT u FROM User u WHERE u.userLoginId = :userLoginId")
    Optional<User> findByUserId(@Param("userLoginId") String userLoginId);
}
