package com.tenco.jobpotal.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompSubJpaRepository extends JpaRepository<CompSub, Long> {

    @Query("SELECT cs FROM CompSub cs JOIN FETCH cs.user u JOIN FETCH cs.compInfo c WHERE c.companyId = :companyId")
    List<CompSubResponse.SubListDTO> findAllByUserAndCompanyId(@Param("companyId") Long companyId);

    @Query("select count(*) > 0 from CompSub cs where cs.compInfo.id = :companyId and cs.user.id = :userId")
    boolean existsByCompanyIdAndUserId(Long companyId, Long userId);

//    @Query("SELECT us FROM UserSub us JOIN FETCH us.user u JOIN FETCH us.compInfo c WHERE u.userId = :userId")
//    List<UserSubResponse.SubListDTO> findAllByUserAndCompanyId(@Param("userId") Long userId);


}
