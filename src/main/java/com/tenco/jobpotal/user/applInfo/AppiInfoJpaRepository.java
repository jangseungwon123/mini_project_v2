//package com.tenco.jobpotal.user.appl_info;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface AppiInfoJpaRepository {
//
//    @Query("SELECT a FROM AppiInfo a " +
//            "JOIN FETCH a.resume.user u " +
//            "JOIN FETCH a.JobPost b " +
//            "WHERE b.compUser.compUserId = :companyId")
//    List<AppiInfo> findAllByBoardIdWithUser(@Param("companyId") Long companyId);
//
//    //
//    @Query("SELECT a FROM AppiInfo a " +
//            "JOIN FETCH a.resume.user u " +
//            "JOIN FETCH a.JobPost b " +
//            "WHERE b.compUser.compUserId = :companyId")
//    AppiInfo findAllBy(@Param("companyId") Long companyId);
//
//
//    @Query("SELECT a FROM AppiInfo a JOIN FETCH a.JobPost b WHERE a.resume.user.userId = :userId")
//    List<AppiInfo> findAllByUserIdWithBoard(@Param("userId") Long userId);
//
//    //
//    @Query("SELECT a FROM AppiInfo a JOIN FETCH a.JobPost b WHERE a.resume.user.userId = :userId")
//    AppiInfo findById(@Param("userId") Long userId);
//
//
//    @Query("SELECT COUNT(a) > 0 FROM AppiInfo a WHERE a.resume.userId = :userId AND a.JobPost.recruitId = :recruitId")
//    boolean existsByUserIdAndBoardId(@Param("userId") Long userId, @Param("recruitId") Long recruitId);
//
//
//    @Query("SELECT a FROM AppiInfo a WHERE a.resume.userId = :userId AND a.JobPost.recruitId = :recruitId")
//    AppiInfo findByApplicationId(@Param("userId") Long userId, @Param("recruitId") Long recruitId);
//
//    @Query("SELECT a FROM Application a JOIN FETCH a.resume WHERE a.resume.userId = :userId AND a.applInfoId = :applInfoId")
//    List<AppiInfo> findByUserIdAndAppiInfo(@Param("userId") Long userId, @Param("applInfoId") Long applInfoId);
//
//}
