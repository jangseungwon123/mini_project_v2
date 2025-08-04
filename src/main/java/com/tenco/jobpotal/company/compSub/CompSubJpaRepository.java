package com.tenco.jobpotal.company.compSub;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompSubJpaRepository extends JpaRepository<CompSub, Long> {

    @Query("SELECT cs FROM CompSub cs JOIN FETCH cs.user u JOIN FETCH cs.compInfo c WHERE c.id = :id")
    List<CompSub> findAllByUserAndCompId(@Param("id") Long id);

    @Query("select count(*) > 0 from CompSub cs where cs.compInfo.id = :compId and cs.user.id = :userId")
    boolean existsByCompanyIdAndUserId(Long companyId, Long userId);

}
