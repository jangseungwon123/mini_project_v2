package com.tenco.jobpotal.job_post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {


    // 제목이나 내용에 키워드가 포함된 공고 검색
    @Query("SELECT j FROM JobPost j WHERE j.title LIKE %:keyword% OR j.content LIKE %:keyword%")
    List<JobPost> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT j FROM JobPost j join fetch j.compInfo WHERE j.recruitId = : jobPostId")
    Optional<JobPost> findByJobPostId(@Param("jobPostId")Long jobPostId);
}


