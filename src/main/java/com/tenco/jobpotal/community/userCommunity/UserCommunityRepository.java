package com.tenco.jobpotal.community.userCommunity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserCommunityRepository extends JpaRepository<UserCommunity, Long> {
    

    Page<UserCommunity> findAll(Pageable pageable);
    
    // 유저 정보를 조인 페치로 가져옴
    @Query("SELECT c FROM UserCommunity c LEFT JOIN FETCH c.user u")
    Page<UserCommunity> findAllWithUser(Pageable pageable);
    
}