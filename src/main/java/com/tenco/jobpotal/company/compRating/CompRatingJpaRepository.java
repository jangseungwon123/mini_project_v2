//package com.tenco.jobpotal.company.comp_rating;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//public interface CompRatingJpaRepository {
//
//    @Query("SELECT COUNT(r) > 0 FROM CompRating r WHERE r.AppiInfo.User.userId = :userId AND r.AppiInfo.CompUser.compUserId = :compUserId")
//    boolean existsByUserIdAndCompUserId(@Param("userId") Long userId, @Param("compUserId") Long compUserId);
//
//    @Query("SELECT r FROM CompRating r WHERE r.AppiInfo.User.userId = :userId AND r.AppiInfo.CompUser.compUserId = :compUserId")
//    CompRating findByUserIdAndCompUserId(@Param("userId") Long userId, @Param("compUserId") Long compUserId);
//
//    @Query("SELECT AVG(r.score) FROM CompRating r WHERE r.AppiInfo.CompUser.compUserId = :compUserId")
//    Double findAvgScoreByCompUserId(@Param("compUserId") Long compUserId);
//
//}
