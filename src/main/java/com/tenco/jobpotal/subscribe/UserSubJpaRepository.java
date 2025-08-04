package com.tenco.jobpotal.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserSubJpaRepository extends JpaRepository<UserSub, Long> {

    @Query("SELECT us FROM UserSub us JOIN FETCH us.user u JOIN FETCH us.compInfo c WHERE u.userId = :userId")
    List<UserSubResponse.SubListDTO> findAllByUserAndCompanyId(@Param("userId") Long userId);

    @Query("select count(*) > 0 from UserSub us where us.compInfo.id = :companyId and us.user.userId = :userId")
    boolean existsByCompanyIdAndUserId(@Param("companyId") Long companyId, @Param("userId")Long userId);

}
