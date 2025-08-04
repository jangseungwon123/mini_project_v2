package com.tenco.jobpotal.reply;

import com.tenco.jobpotal.user.comp.CompUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "comp_comm_cmt")
@Entity
public class CompCommCmt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compCommCmtId;

    // 기업 커뮤니티 게시글 PK 넣기

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_user_id",nullable = false)
    private CompUser compUser;

    @Column(nullable = false,length = 500)
    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

}
