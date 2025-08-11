package com.tenco.jobpotal.company.compRating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompRatingJpaRepository extends JpaRepository<CompRating , Long> {

//    @Query("SELECT COUNT(r) > 0 FROM CompRating r WHERE r.AppiInfo.User.userId = :userId AND r.AppiInfo.CompUser.compUserId = :compUserId")
//    boolean existsByUserIdAndCompUserId(@Param("userId") Long userId, @Param("compUserId") Long compUserId);
//
//    @Query("SELECT r FROM CompRating r WHERE r.AppiInfo.User.userId = :userId AND r.AppiInfo.CompUser.compUserId = :compUserId")
//    CompRating findByUserIdAndCompUserId(@Param("userId") Long userId, @Param("compUserId") Long compUserId);
//
//    @Query("SELECT AVG(r.score) FROM CompRating r WHERE r.ApplInfo.CompUser.compUserId = :compUserId")
//    Double findAvgScoreByCompUserId(@Param("compUserId") Long compUserId);

//    @Query("SELECT COUNT(r) > 0 FROM CompRating r WHERE r.applInfo.applInfoId = :applInfoId")
//    Optional<CompRating> findByApplInfo_applInfo(@Param("applInfoId") Long applInfoId);

    Optional<CompRating> findByApplInfo_ApplInfoId(Long applInfoId);

//    boolean existByUserIdAndCompanyId(Long userId, Long companyId);

//    @Query("SELECT r FROM CompRating r WHERE r.score = :score")
//    Optional<CompRating> compRating(@Param("score") int score);


}
