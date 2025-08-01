package com.tenco.jobpotal.reply;

import com.tenco.jobpotal.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "job_comm_cmt")
@Entity
public class JobCommCmt {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobCommCmtId;

    // 구직자 커뮤니티 게시글 PK 넣기

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "user_id",nullable = false)
     private User user;

    @Column(nullable = false,length = 500)
    private String content;

    @CreationTimestamp
    private Timestamp createdAt;
}
