package com.tenco.jobpotal.reply.job_reply;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JobCommCmtJPARepository extends JpaRepository<JobCommCmt,Long> {

    @Query("SELECT j FROM JobCommCmt j JOIN FETCH j.community c ORDER BY j.id DESC")
    Page<JobCommCmt> findAllJoinCommunity(Pageable pageable);

    @Query("SELECT j FROM JobCommCmt j JOIN FETCH j.community c WHERE j.id = :id")
    Optional<JobCommCmt> findByIdJoinCommunity(@Param("id") Long id);

}
