package com.tenco.jobpotal.resume;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "resume")
@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeId;

    // userid
    // skillListId
    // userSkillListNo
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 1000)
    private String content;
    @Column(nullable = false, length = 1)
    private char isExperienced;
    @Column(nullable = false, length = 1)
    private char isShow;

    @CreationTimestamp
    private Timestamp instDate;



}
