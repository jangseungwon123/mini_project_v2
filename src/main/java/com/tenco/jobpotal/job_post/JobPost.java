package com.tenco.jobpotal.job_post;


import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_post")
@Getter
@Setter
@NoArgsConstructor
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recruitId;

    private Integer compId;

    private String title;

    private String content;

    private String requireCareerYears;

    private String employmentType;

    private String instId;

    @CreationTimestamp
    private LocalDateTime instDate;

    private LocalDateTime postedAt;

    private LocalDateTime deadline;

    public JobPost(Integer recruitId, Integer compId, String title, String content,
                   String requireCareerYears, String employmentType, String instId,
                   LocalDateTime instDate, LocalDateTime postedAt, LocalDateTime deadline) {
        this.recruitId = recruitId;
        this.compId = compId;
        this.title = title;
        this.content = content;
        this.requireCareerYears = requireCareerYears;
        this.employmentType = employmentType;
        this.instId = instId;
        this.instDate = instDate;
        this.postedAt = postedAt;
        this.deadline = deadline;
    }
}


