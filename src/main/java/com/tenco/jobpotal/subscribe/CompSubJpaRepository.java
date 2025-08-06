package com.tenco.jobpotal.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompSubJpaRepository extends JpaRepository<CompSub, Long> {

    @Query("SELECT cs FROM CompSub cs JOIN FETCH cs.user u JOIN FETCH cs.compInfo c WHERE c.compId = :compId")
    List<CompSubResponse.SubListDTO> findAllByUserAndCompanyId(@Param("compId") Long compId);

    @Query("select count(*) > 0 from CompSub cs where cs.compInfo.compId = :compId and cs.user.userId = :userId")
    boolean existsByCompanyIdAndUserId(@Param("compId") Long compId, @Param("userId") Long userId);

//    @Query("SELECT us FROM UserSub us JOIN FETCH us.user u JOIN FETCH us.compInfo c WHERE u.userId = :userId")
//    List<UserSubResponse.SubListDTO> findAllByUserAndCompanyId(@Param("userId") Long userId);


}
