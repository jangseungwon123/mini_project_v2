package com.tenco.jobpotal.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompInfoJpaRepository extends JpaRepository<CompInfo, Long> {

    //@Query("SELECT c FROM CompInfo c JOIN FETCH b.user u ORDER BY b.id DESC")
    @Query("SELECT c FROM CompInfo c ORDER BY c.id DESC")
    Page<CompInfo> findAllCompInfo(Pageable pageable);
}