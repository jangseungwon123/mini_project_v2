package com.tenco.jobpotal.scrap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompScrapJpaRepository extends JpaRepository<CompScrap, Long> {

    @Query("SELECT us FROM UserSub us JOIN FETCH us.user u JOIN FETCH us.compInfo c WHERE u.userId = :userId")
    List<CompScrapResponse.SubListDTO> findAllByUserAndCompId(@Param("userId") Long userId);

    @Query("select count(*) > 0 from UserSub us where us.compInfo.id = :compId and us.user.userId = :userId")
    boolean existsByCompIdAndUserId(@Param("compId") Long compId, @Param("userId")Long userId);

}
