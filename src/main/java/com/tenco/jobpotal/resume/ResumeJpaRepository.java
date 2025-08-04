package com.tenco.jobpotal.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ResumeJpaRepository extends JpaRepository<Resume,Long> {

    @Query("SELECT r FROM Resume r JoIN FETCH r.user u WHERE r.id = :id")
    Optional<Resume> findByIdJoinUser(@Param("id") Long id);

}
